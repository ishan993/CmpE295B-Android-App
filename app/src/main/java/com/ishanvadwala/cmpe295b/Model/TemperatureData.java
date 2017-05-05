package com.ishanvadwala.cmpe295b.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by ishanvadwala on 5/4/17.
 */
public class TemperatureData implements Parcelable {
    String id;
    double temperature;
    int day, month, year;

    public TemperatureData(String id, double temperature, Date date){
        this.id = id;
        this.temperature = temperature;
        this.day = date.getDate();
        this.month = date.getMonth();
        this.year = date.getYear();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeDouble(this.temperature);
        dest.writeInt(this.day);
        dest.writeInt(this.month);
        dest.writeInt(this.year);
    }

    protected TemperatureData(Parcel in) {
        this.id = in.readString();
        this.temperature = in.readDouble();
        this.day = in.readInt();
        this.month = in.readInt();
        this.year = in.readInt();
    }

    public static final Parcelable.Creator<TemperatureData> CREATOR = new Parcelable.Creator<TemperatureData>() {
        @Override
        public TemperatureData createFromParcel(Parcel source) {
            return new TemperatureData(source);
        }

        @Override
        public TemperatureData[] newArray(int size) {
            return new TemperatureData[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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

    public static Creator<TemperatureData> getCREATOR() {
        return CREATOR;
    }
}
