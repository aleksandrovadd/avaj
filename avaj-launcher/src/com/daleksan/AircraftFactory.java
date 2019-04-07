package com.daleksan;

public class AircraftFactory {

    private AircraftFactory() {

    }

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {

        switch (type) {
            case "JetPlane":
                return new JetPlane(name, new Coordinates(longitude, latitude, height), type);
            case "Baloon":
                return new Baloon(name, new Coordinates(longitude, latitude, height), type);
            case "Helicopter":
                return new Helicopter(name, new Coordinates(longitude, latitude, height), type);
                default:
                    System.out.println("Type of Flyable is not recognized.");
                    System.exit(0);
                    return null;
        }
    }
}
