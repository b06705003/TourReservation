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

    private List<Tour> searchedTours;

    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        orderDao = db.orderDao();
        tourDao = db.tourDao();
        travelCodeDao = db.travelCodeDao();
        userDao = db.userDao();
    }

    public List<Tour> getToursByCodeAndDate(int code, Date from, Date to, String sort_method) {
        searchedTours = tourDao.getToursByCodeAndDate(code, from, to, sort_method);
        return searchedTours;
    }

    public List<Integer> getTravelCodesByString(String search_str) {
        return travelCodeDao.getTravelCodesByString(search_str);
    }

    public List<Integer> getAllCodes() {
        return travelCodeDao.getAllCodes();
    }

    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }


}
