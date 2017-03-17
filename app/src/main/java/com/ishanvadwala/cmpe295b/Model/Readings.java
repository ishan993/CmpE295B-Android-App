package com.ishanvadwala.cmpe295b.Model;

/**
 * Created by ishanvadwala on 3/9/17.
 */
public class Readings {
    private String id;
    private float temp, pressure, humidity;

    public Readings(String id, float temp, float pressure, float humidity){
        this.id = id;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
    }
}
