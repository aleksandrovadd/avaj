package com.daleksan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Simulation implements LogListener{

    private int count = 0;
    private List<Flyable> flyableList;
    private WeatherTower weatherTower;
    private List<String> logs;
    private List<String> scenarioString;

    public Simulation(){
        flyableList = new ArrayList<>();
        weatherTower = new WeatherTower();
        logs = new ArrayList<>();
        scenarioString = new ArrayList<>();
    }

    public void start(String scenario) {
        try(BufferedReader br = new BufferedReader(new FileReader(scenario))) {
            String s;
            int i = 0;
            while ((s = br.readLine()) != null) {
                if (i == 0) {
                    try {
                        count = Integer.valueOf(s);
                    } catch (NumberFormatException ex) {
                        System.out.print("First line is not an integer number");
                        System.exit(0);
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
        } catch(IOException ex) {
            if (ex instanceof FileNotFoundException) {
                System.out.println("File doesn't exist");
            } else {
                System.out.println(ex.getMessage());
            }
            System.exit(0);
        }

        for (String s : scenarioString) {
            flyableList.add(parseScenario(s));
        }

        for (Flyable f : flyableList) {
            weatherTower.register(f);
        }

        simulate();

        System.out.println(logs.size());
        writeInFile();
    }

    private void simulate() {
        for (int i = 0; i < count; i++) {
            weatherTower.conditionChanged();
        }
    }

    private Flyable parseScenario(String s) {
        String[] arr = s.split(" ");
        Flyable newAir = AircraftFactory.newAircraft(arr[0], arr[1], Integer.valueOf(arr[2]), Integer.valueOf(arr[3]), Integer.valueOf(arr[4]));
        newAir.setLogListener(this);
        newAir.registerTower(weatherTower);
        return newAir;
    }

    private boolean checkScenario(String s) {
        String[] arr = s.split(" ");
        if (arr.length != 5) {
            return false;
        } else {
            if (!arr[2].matches("[+]?\\d+") || !arr[3].matches("[+]?\\d+") || !arr[4].matches("[+]?\\d+")) {
                return false;
            }
        }
        return true;
    }

    private void writeInFile() {
        try(BufferedWriter br = new BufferedWriter(new FileWriter("simulation.txt")))
        {
            for(String line : logs) {
                br.write(line);
                br.write("\n");
            }
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void onLog(String log) {
        logs.add(log);
    }
}

