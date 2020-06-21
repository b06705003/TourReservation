package com.oop.tourreservation.Entity;

import android.util.Log;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Entity(tableName = "tour")
public class Tour {

    private static final double ADULT_DISCOUNT = 1.0;
    private static final double CHILD_DISCOUNT = 0.75;
    private static final double BABY_DISCOUNT = 0.5;


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

    @Override
    public String toString() {
        return id + ": " + title + " " +  start_date + " " + end_date;
    }

    public Tour(String title, int travel_code, String product_key, int price, String start_str, String end_str, int lower_bound, int upper_bound) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date start_date, end_date;

        try {
            start_date = dateFormat.parse(start_str);
            end_date = dateFormat.parse(end_str);
            this.start_date = start_date;
            this.end_date = end_date;
        }
        catch (ParseException e) {
            Log.d("wrong date", "wrong date");
        }

        this.id = 0;
        this.title = title;
        this.travel_code = travel_code;
        this.product_key = product_key;
        this.price = price;

        this.lower_bound = lower_bound;
        this.upper_bound = upper_bound;
        this.current_amount = 0;

    }



    public boolean addTourists(int num) {
        if (current_amount + num > upper_bound) {
            return false;
        } else {
            current_amount += num;
            return true;
        }
    }

    public int totalPrice(int adult_num, int child_num, int baby_num) {
        return (int) (price * (adult_num*ADULT_DISCOUNT + child_num*CHILD_DISCOUNT + baby_num*BABY_DISCOUNT));
    }
}
