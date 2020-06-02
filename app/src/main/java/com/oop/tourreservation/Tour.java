package com.oop.tourreservation;

import android.util.Log;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public static ArrayList<Tour> createToursList() {
        ArrayList<Tour> tours = new ArrayList<Tour>();

        tours.add(new Tour("馬達加斯加 猴麵包樹 夢幻生態天堂10天", 100, "VDR0000007686", 155900,
                "2020-03-12", "2020-03-21", 16, 16));
        tours.add(new Tour("馬達加斯加 猴麵包樹 夢幻生態天堂10天", 100, "VDR0000007686", 148900,
                "2020-09-03", "2020-09-12", 16, 16));
        tours.add(new Tour("【波蘭、波羅的海三小國、俄羅斯】精彩12 日", 101, "VDR0000001255", 59900,
                "2020-03-19", "2020-03-30", 31, 31));
        tours.add(new Tour("【波蘭、波羅的海三小國、俄羅斯】精彩12 日", 101, "VDR0000001255", 79900,
                "2020-09-03", "2020-09-14", 31, 31));
        tours.add(new Tour("[春櫻紛飛遊釜慶]世界文化遺產~佛國寺、CNN評選賞櫻推薦~余佐川羅曼史橋+慶和火車站、甘川洞彩繪壁畫村、BIFF廣場+南浦洞購物樂五日<含稅>", 342, "VDR0000007614", 12700,
                "2020-03-12", "2020-03-16", 16, 20));
        tours.add(new Tour("揪愛玩無購物#世紀傳奇～金邊風情、吳哥奇景、舒壓SPA、華麗自助餐五日(含稅簽、無購物站)", 391, "VDR0000001838", 24800,
                "2020-03-12", "2020-03-16", 16, 18));
        tours.add(new Tour("特選金邊吳哥～一次遊雙城、入住精選飯店、市場低價(含稅)", 391, "VDR0000001846", 16900,
                "2020-03-12", "2020-03-16", 15, 16));
        tours.add(new Tour("特選金邊吳哥～一次遊雙城、入住精選飯店、市場低價(含稅)", 391, "VDR0000001846", 16900,
                "2020-03-13", "2020-03-17", 15, 16));
        tours.add(new Tour("【國航假期】東歐純情三國+波蘭8日", 395, "VDR0000001842", 39600,
                "2020-03-12", "2020-03-19", 25, 35));
        tours.add(new Tour("【醉愛歐洲波波8日】~波蘭.波羅地海三小國 愛沙尼亞 拉脫維亞 立陶宛", 395, "VDR0000001843", 39600,
                "2020-03-12", "2020-03-19", 25, 25));
        tours.add(new Tour("馬達加斯加 猴麵包樹 夢幻生態天堂10天", 100, "VDR0000007686", 158900,
                "2020-06-25", "2020-07-04", 16, 16));
        tours.add(new Tour("【10人成行】就愛釜慶邱. 愛來水族館. 伽倻王國彩繪秀+星光庭院.甘川洞文化村五日", 342, "VDR0000001839", 23900,
                "2020-06-25", "2020-06-29", 10, 16));

        return tours;
    }
}
