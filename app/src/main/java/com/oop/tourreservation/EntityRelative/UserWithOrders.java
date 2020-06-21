// test github edit
package com.oop.tourreservation.EntityRelative;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.oop.tourreservation.Entity.*;

import java.util.List;

public class UserWithOrders {

    @Embedded public User user;
    @Relation(
            parentColumn = "id",
            entityColumn = "user_id"
    )
    public List<Order> orders;
}
