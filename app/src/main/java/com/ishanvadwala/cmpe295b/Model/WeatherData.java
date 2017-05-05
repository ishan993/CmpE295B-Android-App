package com.ishanvadwala.cmpe295b.Model;

import java.util.Date;



/**
 * Created by ishanvadwala on 3/9/17.
 */
public class WeatherData{
    private String id;

    private int date, month, year;

    private double temp, pressure, humidity;

    public WeatherData(){

    }

    public WeatherData(String id, double temp, double pressure, double humidity, Date date){
        this.id = id;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.date = date.getDate();
        this.month = date.getMonth();
        this.year = date.getYear();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public int getDate() {
        return date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDate(Date date){
        this.date = date.getDate();
        this.month = date.getMonth();
        this.year = date.getYear();
    }
}
