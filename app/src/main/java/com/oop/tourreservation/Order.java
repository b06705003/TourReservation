package com.oop.tourreservation;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "order")
public class Order {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int user_id;
    public int tour_id;
    public int adult_num;
    public int child_num;
    public int baby_num;
    public int total_price;

    @Ignore
    public Order(int id, int user_id, int tour_id, int adult_num, int child_num, int baby_num, int total_price) {
        this.id = id;
        this.user_id = user_id;
        this.tour_id = tour_id;
        this.adult_num = adult_num;
        this.child_num = child_num;
        this.baby_num = baby_num;
        this.total_price = total_price;
    }

    public Order(int user_id, int tour_id, int adult_num, int child_num, int baby_num, int total_price) {
        this(0, user_id, tour_id, adult_num, child_num, baby_num, total_price);
    }
}
