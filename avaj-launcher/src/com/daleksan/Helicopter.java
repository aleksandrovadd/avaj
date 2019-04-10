package com.daleksan;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;
    private String message;

    protected Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {

        switch (WeatherProvider.getProvider().getCurrentWeather(coordinates)) {
            case "FOG":
                coordinates.setLongitude(coordinates.getLongitude() + 1);
                message = "I can't see anything! It's foggy now!";
                break;
            case "RAIN":
                coordinates.setLongitude(coordinates.getLongitude() + 5);
                message = "It's raining!";
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 12);
                message = "It's freezing!";
                break;
            case "SUN":
                coordinates.setLongitude(coordinates.getLongitude() + 10);
                coordinates.setHeight(coordinates.getHeight() + 2);
                message = "It's hot! and bright";
                break;
            default:
                break;
        }
        if (coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
            message = "We are landing at coordinates: " + coordinates.getLongitude() + " " + coordinates.getLatitude() + " " + 0;
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
        return Aircraft.Helicopter + super.toString();
    }
}
