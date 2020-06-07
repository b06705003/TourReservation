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

    public User getUserByUsername(String username) { return userDao.getUserByUsername(username); }

    public void insertOrder(Order order) { orderDao.insertOrder(order); }

    public List<Order> getAllOrders() { return orderDao.getAllOrders(); }

    public void updateTour(Tour tour) { tourDao.updateTour(tour); }

    public Order getOrder(int orderId) { return orderDao.getOrder(orderId); }

    public User getUser(int userId) { return userDao.getUser(userId); }

    public Tour getTour(int tourId) { return tourDao.getTour(tourId); }

    public void deleteOrder(Order order) { orderDao.deleteOrder(order); }

    public void updateOrder(Order order) { orderDao.updateOrder(order);}
}
