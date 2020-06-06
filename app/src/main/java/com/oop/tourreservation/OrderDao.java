package com.oop.tourreservation;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrderDao {

    @Insert
    void insertOrder(Order order);

    @Query("SELECT * FROM `order` WHERE id = :orderId LIMIT 1")
    Order getOrder(int orderId);

    @Query("SELECT * FROM `order`")
    List<Order> getAllOrders();
}
