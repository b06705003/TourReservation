package com.oop.tourreservation.EntityRelative;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.oop.tourreservation.Entity.*;

import java.util.List;

public class TourWithOrders {

    @Embedded public Tour tour;
    @Relation(
            parentColumn = "id",
            entityColumn = "tour_id"
    )
    public List<Order> orders;
}
