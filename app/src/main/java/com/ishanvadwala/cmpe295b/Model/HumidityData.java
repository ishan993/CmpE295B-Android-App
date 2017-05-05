package com.ishanvadwala.cmpe295b.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by ishanvadwala on 5/4/17.
 */
public class HumidityData implements Parcelable{
    String id;
    double humidity;
    int day, month, year;

    public HumidityData(String id, double humidity, Date date){
        this.id = id;
        this.humidity = humidity;
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
        dest.writeDouble(this.humidity);
        dest.writeInt(this.day);
        dest.writeInt(this.month);
        dest.writeInt(this.year);
    }

    protected HumidityData(Parcel in) {
        this.id = in.readString();
        this.humidity = in.readDouble();
        this.day = in.readInt();
        this.month = in.readInt();
        this.year = in.readInt();
    }

    public static final Creator<HumidityData> CREATOR = new Creator<HumidityData>() {
        @Override
        public HumidityData createFromParcel(Parcel source) {
            return new HumidityData(source);
        }

        @Override
        public HumidityData[] newArray(int size) {
            return new HumidityData[size];
        }
    };
}
