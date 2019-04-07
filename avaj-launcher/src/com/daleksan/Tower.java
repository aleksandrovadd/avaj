package com.daleksan;

import java.util.List;

public abstract class Tower {

    public abstract void register(Flyable flyable);
    public abstract void unregister(Flyable flyable);
    public abstract void conditionChanged();
}
