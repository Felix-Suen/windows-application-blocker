package com.blocker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.util.HashSet;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class App extends TimerTask {
    //public HashSet<String> set = new HashSet<String>();

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

    public void run() {
        // App block = new App();
        ArrayList<String> apps = new ArrayList<String>();
        apps.add("Discord.exe");
        apps.add("Todoist.exe");
        apps.add("VALORANT.exe");
        apps.add("VALORANT-Win64-Shipping.exe");

        findAndKill(apps);
    }
}
