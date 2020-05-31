package com.oop.tourreservation;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TravelCodeWithTours {

    @Embedded public TravelCode travelCode;
    @Relation(
            parentColumn = "id",
            entityColumn = "travel_code"
    )
    public List<Tour> tours;
}
