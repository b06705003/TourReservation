package com.oop.tourreservation;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

public class AppRepository {

    private OrderDao orderDao;
    private TourDao tourDao;
    private TravelCodeDao travelCodeDao;
    private UserDao userDao;

    private LiveData<List<Tour>> searchedTours;

    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        orderDao = db.orderDao();
        tourDao = db.tourDao();
        travelCodeDao = db.travelCodeDao();
        userDao = db.userDao();
    }

    public LiveData<List<Tour>> getToursByCodeAndDate(int code, Date date) {
        searchedTours = tourDao.getToursByCodeAndDate(code, date);
        return searchedTours;
    }
}
