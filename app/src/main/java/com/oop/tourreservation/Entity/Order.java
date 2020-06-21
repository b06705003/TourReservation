package com.oop.tourreservation.Entity;

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

    // constructor when creating an order
    public Order(int user_id, int tour_id, int adult_num, int child_num, int baby_num, int total_price) {
        this(0, user_id, tour_id, adult_num, child_num, baby_num, total_price);
    }

    @Override
    public String toString() { return id + ", " + user_id + ", " + tour_id; }

    // get total # of people in this order
    public int getTotalNum() { return adult_num + child_num + baby_num; }

    // for user to modify his or her order
    public void modify(int adult_num, int child_num, int baby_num, int total_price) {
        this.adult_num = adult_num;
        this.child_num = child_num;
        this.baby_num = baby_num;
        this.total_price = total_price;
    }

    // to notify user of his or her order info
    public String getID() { return "訂單標號："+ id + ", 使用者：" + user_id + ", 行程標號" + tour_id; }

}
