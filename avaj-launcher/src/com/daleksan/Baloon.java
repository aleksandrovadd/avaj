package com.daleksan;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;
    private String message;


    protected Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        switch (WeatherProvider.getProvider().getCurrentWeather(coordinates)) {
            case "FOG":
                coordinates.setHeight(coordinates.getHeight() - 3);
                message = "I can't see anything! It's foggy now!";
                break;
            case "RAIN":
                coordinates.setHeight(coordinates.getHeight() - 5);
                message = "It's watery! Give me some towel";
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 15);
                message = "It's freezing! Stuard, bring me hot tea!";
                break;
            case "SUN":
                coordinates.setLongitude(coordinates.getLongitude() + 2);
                coordinates.setHeight(coordinates.getHeight() + 4);
                message = "It's hot! and bright";
                break;
            default:
                break;
        }
        if (coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
            message = ("We are landing at coordinates: " + coordinates.getLongitude() + " " + coordinates.getLatitude() + " " + 0);
        }
        if (coordinates.getHeight() > 100) {
            coordinates.setHeight(100);
        }
        LogListener.log(toString() + ": " + message);
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }

    @Override
    public String toString() {
        return Aircraft.Baloon + super.toString();
    }
}
