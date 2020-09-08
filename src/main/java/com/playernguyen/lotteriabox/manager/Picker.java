package com.playernguyen.lotteriabox.manager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Picker<T> {

    private final Map<T, Double[]> map = new HashMap<>();
    private double currentMaximum = 0.0D;

    public Picker() {
    }

    public void add(T item, double rate) {
        if (rate <= 0) {
            throw new IllegalStateException("Rate must greater than zero (0)");
        }
        // Put to the next
        Double[] d = new Double[]{currentMaximum, currentMaximum + rate};
        this.map.put(item, d);
        // Current maximum changing
        currentMaximum += rate;


    }

    public T pick() {
        return pointing(random(currentMaximum));
    }

    private T pointing(double p) {
        for (T element : map.keySet()) {
            Double[] ranges = map.get(element);
            if (p < ranges[1] && p > ranges[0]) {
                return element;
            }
        }
        throw new IllegalStateException("Out of range with pointing " + p + " in maximum " + currentMaximum);
        // return pointing(random(currentMaximum));
    }

    private double random(double rangeMax) {
        Random r = new Random();
        return (double) 0 + (rangeMax - (double) 0) * r.nextDouble();
    }

    @Override
    public String toString() {
        return "Picker{" +
                "map=" + map +
                ", currentMaximum=" + currentMaximum +
                '}';
    }
}
