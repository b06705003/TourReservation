package com.oop.tourreservation;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OrderDao {

    @Insert
    void insertOrder(Order order);

    @Query("SELECT * FROM `order` WHERE id = :orderId LIMIT 1")
    Order getOrder(int orderId);

    @Query("SELECT * FROM `order`")
    List<Order> getAllOrders();

    @Delete
    void deleteOrder(Order order);

    @Update
    void updateOrder(Order order);
}
