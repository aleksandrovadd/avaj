package com.daleksan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private static List<Flyable> flyableList = new ArrayList<>();
    private static WeatherTower weatherTower = new WeatherTower();
    private static List<String> scenarioString = new ArrayList<>();
    private static int count = 0;

        public static void main(String[] args) {

        try {

            BufferedWriter wr = new BufferedWriter(new FileWriter("simulation.txt"));
            new LogListener(wr);

            if (args.length != 1 || (args[0] == null && args[0].isEmpty()))
                throw new IllegalArgumentException("No file! Please check.");
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String s;
            int i = 0;
            while ((s = br.readLine()) != null) {
                if (i == 0) {
                    try {
                        count = Integer.valueOf(s);
                    }
                    catch (IllegalArgumentException ex) {
                        System.out.println("First line is not an integer number");
                    }
                    if (count < 0) {
                        System.out.println("First line is not a positive integer number");
                        System.exit(0);
                    }
                } else {
                    if (!checkScenario(s)) {
                        System.out.println("One of parameters is not a positive number");
                        System.exit(0);
                    } else {
                        scenarioString.add(s);
                    }
                }
                i = -1;
            }
            br.close();

            for (String s1 : scenarioString) {
                flyableList.add(parseScenario(s1));
            }

            for (Flyable f : flyableList) {
                weatherTower.register(f);
            }

            simulate(count);
            wr.close();
        } catch(IOException ex) {
            ex.printStackTrace();
            System.exit(0);
        }
    }

        private static void simulate(int count) {
            for (int i = 0; i < count; i++) {
                weatherTower.conditionChanged();
            }
        }

        private static Flyable parseScenario(String s) {
            String[] arr = s.split(" ");
            Flyable newAir = AircraftFactory.newAircraft(arr[0], arr[1], Integer.valueOf(arr[2]), Integer.valueOf(arr[3]), Integer.valueOf(arr[4]));
            newAir.registerTower(weatherTower);
            return newAir;
        }

        private static boolean checkScenario(String s) {
            String[] arr = s.split(" ");
            if (arr.length != 5) {
                return false;
            } else if (!arr[2].matches("[+]?\\d+") || !arr[3].matches("[+]?\\d+") || !arr[4].matches("[+]?\\d+")) {
                return false;
            }
            return true;
        }
}


