package com.travel.voyage.retrofit_and_weather;

import com.google.gson.annotations.Expose;
import com.google.gson.internal.LinkedTreeMap;
/**
 * @author Beti
 */
public class Weather {
    @Expose
    private LinkedTreeMap main;
    @Expose
    private LinkedTreeMap wind;
    @Expose
    private LinkedTreeMap clouds;

    public int getCurrentTemperature() {
        return (int) Math.ceil((double) main.get("temp"));
    }

    public int getMinTemperature() {
        return (int) Math.ceil((double) main.get("temp_min"));
    }

    public int getMaxTemperature() {
        return (int) Math.ceil((double) main.get("temp_max"));
    }

    public int getWind() {
        return (int) Math.ceil((double) wind.get("speed"));
    }

    public int getClouds() {
        return (int) Math.ceil((double) clouds.get("all"));
    }

    public int getHumidity() {
        return (int) Math.ceil((double) main.get("humidity"));
    }

    @Override
    public String toString() {
        return "Weather{" +
                "main=" + main +
                ", wind=" + wind +
                ", clouds=" + clouds +
                '}';
    }
}
