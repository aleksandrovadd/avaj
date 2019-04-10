package com.daleksan;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class WeatherTower extends Tower {

    private List<Flyable> observers;

    public WeatherTower() {
        observers = new CopyOnWriteArrayList<>();
    }

    @Override
    public void register(Flyable flyable) {
        LogListener.log("Tower says: " + flyable + " registered to weather tower");
        observers.add(flyable);
    }

    @Override
    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }

    @Override
    public void conditionChanged() {
        for (Flyable f : observers) {
            f.updateConditions();
        }
    }
}
