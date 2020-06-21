package com.oop.tourreservation.Entity;

import android.util.Log;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "travel_code")
public class TravelCode {
    @PrimaryKey
    public int id;
    public String area_name;

    // constructor for travelCode
    public TravelCode(int id, String area_name) {
        this.id = id;
        this.area_name = area_name;
    }
}
