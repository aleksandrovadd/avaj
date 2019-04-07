package com.daleksan;

public class Main {

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        if (args[0] != null && !args[0].isEmpty())
            simulation.start(args[0]);
        else {
            throw Exception();
            System.out.println("No file! Please check.");
            //написать проверку на фаил
        }
    }
}

