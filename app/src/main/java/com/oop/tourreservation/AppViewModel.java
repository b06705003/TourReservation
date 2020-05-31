package com.oop.tourreservation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

public class AppViewModel extends AndroidViewModel {

    private AppRepository appRepository;

    private LiveData<List<Tour>> searchedTours;
    private LiveData<Order> searchedOrder;

    public AppViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
    }

    // 1st function: search available tours by code and date
    public LiveData<List<Tour>> getToursByCodeAndDate(int code, Date date) {
        searchedTours = appRepository.getToursByCodeAndDate(code, date);
        return searchedTours;
    }

    // 4th function: search existing order by user_id and order_id
    public LiveData<Order> getOrder() {
        // TODO: 2020/5/31
        return searchedOrder;
    }


}
