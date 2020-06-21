package com.oop.tourreservation.EntityRelative;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.oop.tourreservation.Entity.*;

import java.util.List;

public class TravelCodeWithTours {

    @Embedded public TravelCode travelCode;
    @Relation(
            parentColumn = "id",
            entityColumn = "travel_code"
    )
    public List<Tour> tours;
}
