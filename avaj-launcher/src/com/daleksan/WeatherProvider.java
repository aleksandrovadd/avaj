package com.daleksan;

import java.util.List;

public class WeatherProvider {

    private static WeatherProvider instance;
    private static String[] weather = {"FOG", "RAIN", "SNOW", "SUN"};

    private WeatherProvider() {}

    public static WeatherProvider getProvider() {
        if (instance == null) {
            instance = new WeatherProvider();
        }
        return instance;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        if (coordinates.getHeight() > 0 && coordinates.getHeight() <= 25)
           return weather[0];
        else if (coordinates.getHeight() > 25 && coordinates.getHeight() <= 50)
            return weather[1];
        else if (coordinates.getHeight() > 50 && coordinates.getHeight() <= 75)
            return weather[2];
        else if (coordinates.getHeight() > 75)
            return weather[3];
        return "";
    }
}
