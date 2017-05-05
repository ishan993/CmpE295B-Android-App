package com.ishanvadwala.cmpe295b.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by ishanvadwala on 5/4/17.
 */
public class PressureData implements Parcelable {
    String id;
    double atm;
    int day, month, year;

    public PressureData(String id, double atm, Date date){
        this.id = id;
        this.atm = atm;
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
        dest.writeDouble(this.atm);
        dest.writeInt(this.day);
        dest.writeInt(this.month);
        dest.writeInt(this.year);
    }

    protected PressureData(Parcel in) {
        this.id = in.readString();
        this.atm = in.readDouble();
        this.day = in.readInt();
        this.month = in.readInt();
        this.year = in.readInt();
    }

    public static final Parcelable.Creator<PressureData> CREATOR = new Parcelable.Creator<PressureData>() {
        @Override
        public PressureData createFromParcel(Parcel source) {
            return new PressureData(source);
        }

        @Override
        public PressureData[] newArray(int size) {
            return new PressureData[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAtm() {
        return atm;
    }

    public void setAtm(double atm) {
        this.atm = atm;
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

    public static Creator<PressureData> getCREATOR() {
        return CREATOR;
    }
}
