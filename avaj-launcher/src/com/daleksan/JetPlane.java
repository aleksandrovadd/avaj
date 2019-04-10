package com.daleksan;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;
    private String message;

    protected JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {

        switch (WeatherProvider.getProvider().getCurrentWeather(coordinates)) {
            case "FOG":
                coordinates.setLatitude(coordinates.getLatitude() + 1);
                message = "I can't see anything! It's foggy now!";
                break;
            case "RAIN":
                coordinates.setLatitude(coordinates.getLatitude() + 5);
                message = "It's raining!";
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 7);
                message = "It's freezing! Stay warm!";
                break;
            case "SUN":
                coordinates.setLatitude(coordinates.getLatitude() + 10);
                coordinates.setHeight(coordinates.getHeight() + 2);
                message = "It's hot! And bright. Bring some ice!";
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
        return Aircraft.JetPlane + super.toString();
    }
}
