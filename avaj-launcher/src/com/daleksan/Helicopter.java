package com.daleksan;

//import sun.rmi.runtime.Log;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;
    private String type;
    private LogListener logListener;

    protected Helicopter(String name, Coordinates coordinates, String type) {
        super(name, coordinates);
        this.type = type;
        System.out.println(id);
    }

    @Override
    public void updateConditions() {
        switch (WeatherProvider.getProvider().getCurrentWeather(coordinates)) {
            case "FOG":
                coordinates.setLongitude(coordinates.getLongitude() + 1);
                System.out.println(String.format("%s#%s: I can't see anything! It's foggy now!", type, name));
                break;
            case "RAIN":
                coordinates.setLongitude(coordinates.getLongitude() + 5);
                System.out.println(String.format("%s#%s: It's raining!", type, name));
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 12);
                System.out.println(String.format("%s#%s: It's freezing!", type, name));
                break;
            case "SUN":
                coordinates.setLongitude(coordinates.getLongitude() + 10);
                coordinates.setHeight(coordinates.getHeight() + 2);
                System.out.println(String.format("%s#%s: It's hot! and bright", type, name));
                break;
                default:
                    break;
        }
        if (coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
            System.out.println(String.format("Tower says: %s#%s Unregister in WeatherTower", type, name));
        }
        if (coordinates.getHeight() > 100) {
            coordinates.setHeight(100);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        System.out.println(String.format("Tower says: %s#%s register in WeatherTower", type, name));
    }

    @Override
    public void setLogListener(LogListener logListener) {
        this.logListener = logListener;
    }
}
