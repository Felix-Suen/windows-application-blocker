package com.blocker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.ArrayList;

public class App {
    public HashSet<String> set = new HashSet<String>();

    public void processes() {
        try {
            Process process = Runtime.getRuntime().exec("tasklist");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                // get rid of useless lines in the command line
                counter++;

                if (counter > 6) {
                    // create a hashset for O(1) search
                    int i = line.indexOf(' ');
                    String word = line.substring(0, i);
                    System.out.println(word);
                    this.set.add(word);
                }

            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public void kill (ArrayList<String> apps) {
        try {
            for (String app : apps) {
                if (set.contains(app)) {
                    Runtime.getRuntime().exec("taskkill /IM " + app + " /F");
                    System.out.println(app + " is now blocked");
                } else {
                    System.out.println(app + " is not found");
                }
            }
        } catch (Exception ie) {
            ie.printStackTrace();
        }
    }

    public static void main(String[] args) {
        App block = new App();
        ArrayList<String> apps = new ArrayList<String>();
        apps.add("Discord.exe");
        apps.add("cmd.exe");
        apps.add("Todoist.exe");
        block.processes();
        block.kill(apps);
    }
}
