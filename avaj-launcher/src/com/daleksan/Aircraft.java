package com.daleksan;

public abstract class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;

    private static long idCounter;


    protected Aircraft(String name, Coordinates coordinates) {
        this.coordinates = coordinates;
        this.name = name;
        this.id = nextId();
    }

    private long nextId() {
        idCounter++;
        return idCounter;
    }

    @Override
    public String toString() {
        return "#" + name + "(" + id + ")";
    }

}
