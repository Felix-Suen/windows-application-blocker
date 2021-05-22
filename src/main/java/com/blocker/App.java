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
            Runtime.getRuntime().exec("taskkill /IM " + "VALORANT-Win64-Shipping.exe" + " /F");
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

    public void findAndKill (ArrayList<String> apps) {
        try {
            for (String app : apps) {
                // command that finds the process
                String find = "tasklist /fi \"IMAGENAME eq " + app + "\"";
                
                Process process = Runtime.getRuntime().exec(find);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                String line;
                int counter = 0;
                line = reader.readLine();

                // check if the process exists, since the String starts with 'I'
                if (line.indexOf('I') == 0) {
                    System.out.println(app + " is not found");
                } else {
                    while ((line = reader.readLine()) != null) {
                        counter++;
                        if (counter > 2) {
                            //System.out.println(line);
                            String pid = line.split("\\s+")[1];
                            System.out.println(pid);
                            Runtime.getRuntime().exec("taskkill /pid " + pid);
                        }
                    }
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
        apps.add("Todoist.exe");
        apps.add("VALORANT.exe");
        apps.add("VALORANT-Win64-Shipping.exe");
        block.findAndKill(apps);
        // block.processes();
        // block.kill(apps);
    }
}
