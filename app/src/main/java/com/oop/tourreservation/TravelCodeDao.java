package com.oop.tourreservation;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TravelCodeDao {

    @Insert
    void insertTravelCode(TravelCode travelCode);

    @Query("SELECT id FROM travel_code WHERE area_name LIKE :search_str")
    public List<Integer> getTravelCodesByString(String search_str);

    @Query("SELECT id FROM travel_code")
    public List<Integer> getAllCodes();
}
