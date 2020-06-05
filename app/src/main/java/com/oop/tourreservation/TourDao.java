package com.oop.tourreservation;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface TourDao {

    @Insert
    void insertTour(Tour tour);

    @Query("SELECT * FROM tour WHERE travel_code = :code AND start_date BETWEEN :from AND :to ORDER BY :sortMethod")
    List<Tour> getToursByCodeAndDate(int code, Date from, Date to, String sortMethod);
}
