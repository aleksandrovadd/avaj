package com.daleksan;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;
    private String type;
    private LogListener logListener;
    private String message;

    public void setLogListener(LogListener logListener) {
        this.logListener = logListener;
    }

    protected Baloon(String name, Coordinates coordinates, String type) {
        super(name, coordinates);
        this.type = type;
        System.out.println(id);
    }

    @Override
    public void updateConditions() {
        switch (WeatherProvider.getProvider().getCurrentWeather(coordinates)) {
            case "FOG":
                coordinates.setHeight(coordinates.getHeight() - 3);
                message = String.format("%s#%s: I can't see anything! It's foggy now!", type, name);
                if (logListener != null)
                    logListener.onLog(message);
                break;
            case "RAIN":
                coordinates.setHeight(coordinates.getHeight() - 5);
                message = String.format("%s#%s: It's raining!", type, name);
                if (logListener != null)
                    logListener.onLog(message);
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 15);
                message = String.format("%s#%s: It's freezing!", type, name);
                if (logListener != null)
                    logListener.onLog(message);
                break;
            case "SUN":
                coordinates.setLongitude(coordinates.getLongitude() + 2);
                coordinates.setHeight(coordinates.getHeight() + 4);
                message = String.format("%s#%s: It's hot! and bright", type, name);
                if (logListener != null)
                    logListener.onLog(message);
                break;
            default:
                break;
        }
        if (coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
            message = String.format("Tower says: %s#%s Unregister in WeatherTower", type, name);
            if (logListener != null)
                logListener.onLog(message);
        }
        if (coordinates.getHeight() > 100) {
            coordinates.setHeight(100);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        message = String.format("Tower says: %s#%s register in WeatherTower", type, name);
        if (logListener != null)
            logListener.onLog(message);
    }
}
