package com.blocker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public boolean blockProcess(String app) {
        try {
            Process process = Runtime.getRuntime().exec("tasklist");
            System.out.println(process);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                if (line.startsWith(app)) {
                    return true;
                }
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        App block = new App();
        block.blockProcess("Discord.exe");
    }
}
