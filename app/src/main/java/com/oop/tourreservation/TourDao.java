package com.oop.tourreservation;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface TourDao {

    @Insert
    void insertTour(Tour tour);

    @Query("SELECT * FROM tour WHERE travel_code = :code AND start_date = :date")
    LiveData<List<Tour>> getToursByCodeAndDate(int code, Date date);
}
