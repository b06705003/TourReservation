package com.oop.tourreservation;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface OrderDao {

    @Insert
    void insertOrder(Order order);

    @Query("SELECT * FROM `order` WHERE id = :orderId LIMIT 1")
    LiveData<Order> getOrder(int orderId);
}
