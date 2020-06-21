package com.oop.tourreservation.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.oop.tourreservation.Entity.Tour;

import java.util.Date;
import java.util.List;

@Dao
public interface TourDao { // get access to Tour order

    @Insert
    void insertTour(Tour tour);

    // for search function
    @Query("SELECT * FROM tour WHERE travel_code = :code AND start_date BETWEEN :from AND :to ORDER BY price ASC")
    List<Tour> getToursByCodeAndDate(int code, Date from, Date to);

    @Update
    void updateTour(Tour tour);

    @Query("SELECT * FROM tour WHERE id = :tourId LIMIT 1")
    Tour getTour(int tourId);
}
