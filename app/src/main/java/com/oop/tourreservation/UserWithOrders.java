package com.oop.tourreservation;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithOrders {

    @Embedded public User user;
    @Relation(
            parentColumn = "id",
            entityColumn = "user_id"
    )
    public List<Order> orders;
}
