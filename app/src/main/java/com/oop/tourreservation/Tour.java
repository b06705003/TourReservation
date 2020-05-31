package com.oop.tourreservation;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tour")
public class Tour {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public int travel_code;
    public String product_key;
    public int price;
    public Date start_date;
    public Date end_date;
    public int lower_bound;
    public int upper_bound;
    public int current_amount;

    public Tour(String title, int travel_code, String product_key, int price, Date start_date, Date end_date, int lower_bound, int upper_bound) {
        this.id = 0;
        this.title = title;
        this.travel_code = travel_code;
        this.product_key = product_key;
        this.price = price;
        this.start_date = start_date;
        this.end_date = end_date;
        this.lower_bound = lower_bound;
        this.upper_bound = upper_bound;
        this.current_amount = 0;
    }
}
