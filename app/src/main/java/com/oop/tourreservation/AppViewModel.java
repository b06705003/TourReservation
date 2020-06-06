package com.oop.tourreservation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

public class AppViewModel extends AndroidViewModel {

    private AppRepository appRepository;

    private List<Tour> searchedTours;
    private Order searchedOrder;

    public AppViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
    }

    // 1st function: search available tours by code and date
    public List<Tour> getToursByCodeAndDate(int code, Date from, Date to, String sort_method) {
        searchedTours = appRepository.getToursByCodeAndDate(code, from, to, sort_method);
        return searchedTours;
    }

    public List<Integer> getTravelCodesByString(String search_str) {
        return appRepository.getTravelCodesByString(search_str);
    }

    public List<Integer> getAllCodes() {
        return appRepository.getAllCodes();
    }

    // 4th function: search existing order by user_id and order_id
    public Order getOrder(int orderId) { return appRepository.getOrder(orderId); }

    public void insertUser(User user) {
        appRepository.insertUser(user);
    }

    public List<User> getAllUsers() {
        return appRepository.getAllUsers();
    }

    public User getUserByUsername(String username) { return appRepository.getUserByUsername(username); }

    public void insertOrder(Order order) { appRepository.insertOrder(order); }

    public List<Order> getAllOrders() { return appRepository.getAllOrders(); }

    void updateTour(Tour tour) { appRepository.updateTour(tour); }

    public User getUser(int userId) { return appRepository.getUser(userId); }

    public Tour getTour(int tourId) { return appRepository.getTour(tourId); }
}