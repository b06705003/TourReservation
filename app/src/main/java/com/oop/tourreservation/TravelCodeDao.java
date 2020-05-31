package com.oop.tourreservation;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface TravelCodeDao {

    @Insert
    void insertTravelCode(TravelCode travelCode);
}
