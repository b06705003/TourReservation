package com.oop.tourreservation;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Tour.class, Order.class, User.class, TravelCode.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract TourDao tourDao();
    public abstract OrderDao orderDao();
    public abstract UserDao userDao();
    public abstract TravelCodeDao travelCodeDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .allowMainThreadQueries()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                TourDao tourDao = INSTANCE.tourDao();
                TravelCodeDao codeDao = INSTANCE.travelCodeDao();

                TravelCode code = new TravelCode(100, "北海道．札幌．小樽．函館．旭川");
                codeDao.insertTravelCode(code);
                code = new TravelCode(101, "關西．大阪．京都．神戶．奈良");
                codeDao.insertTravelCode(code);
                code = new TravelCode(391, "東京．箱根．輕井澤．伊豆．茨城");
                codeDao.insertTravelCode(code);
                code = new TravelCode(395, "東北．青森．仙台．山形．花卷．新潟");
                codeDao.insertTravelCode(code);
                code = new TravelCode(49, "北陸．名古屋．小松．富山．立山黑部");
                codeDao.insertTravelCode(code);
                code = new TravelCode(401, "四國．中國．高松．廣島．岡山");
                codeDao.insertTravelCode(code);
                code = new TravelCode(41, "九州．福岡．宮崎．鹿兒島．佐賀");
                codeDao.insertTravelCode(code);
                code = new TravelCode(80, "琉球．沖繩．石垣島");
                codeDao.insertTravelCode(code);
                code = new TravelCode(98, "首爾．清州");
                codeDao.insertTravelCode(code);
                code = new TravelCode(342, "四國．中國．高松．廣島．岡山");
                codeDao.insertTravelCode(code);

                Tour tour = new Tour("馬達加斯加 猴麵包樹 夢幻生態天堂10天", 100, "VDR0000007686", 155900,
                        "2020-03-12", "2020-03-21", 16, 16);
                tourDao.insertTour(tour);
                tour = new Tour("馬達加斯加 猴麵包樹 夢幻生態天堂10天", 100, "VDR0000007686", 148900,
                        "2020-09-03", "2020-09-12", 16, 16);
                tourDao.insertTour(tour);
                tour = new Tour("【波蘭、波羅的海三小國、俄羅斯】精彩12 日", 101, "VDR0000001255", 59900,
                        "2020-03-19", "2020-03-30", 31, 31);
                tourDao.insertTour(tour);
                tour = new Tour("【波蘭、波羅的海三小國、俄羅斯】精彩12 日", 101, "VDR0000001255", 79900,
                        "2020-09-03", "2020-09-14", 31, 31);
                tourDao.insertTour(tour);
                tour = new Tour("[春櫻紛飛遊釜慶]世界文化遺產~佛國寺、CNN評選賞櫻推薦~余佐川羅曼史橋+慶和火車站、甘川洞彩繪壁畫村、BIFF廣場+南浦洞購物樂五日<含稅>", 342, "VDR0000007614", 12700,
                        "2020-03-12", "2020-03-16", 16, 20);
                tourDao.insertTour(tour);
                tour = new Tour("揪愛玩無購物#世紀傳奇～金邊風情、吳哥奇景、舒壓SPA、華麗自助餐五日(含稅簽、無購物站)", 391, "VDR0000001838", 24800,
                        "2020-03-12", "2020-03-16", 16, 18);
                tourDao.insertTour(tour);
                tour = new Tour("特選金邊吳哥～一次遊雙城、入住精選飯店、市場低價(含稅)", 391, "VDR0000001846", 16900,
                        "2020-03-12", "2020-03-16", 15, 16);
                tourDao.insertTour(tour);
                tour = new Tour("特選金邊吳哥～一次遊雙城、入住精選飯店、市場低價(含稅)", 391, "VDR0000001846", 16900,
                        "2020-03-13", "2020-03-17", 15, 16);
                tourDao.insertTour(tour);
                tour = new Tour("【國航假期】東歐純情三國+波蘭8日", 395, "VDR0000001842", 39600,
                        "2020-03-12", "2020-03-19", 25, 35);
                tourDao.insertTour(tour);
                tour = new Tour("【醉愛歐洲波波8日】~波蘭.波羅地海三小國 愛沙尼亞 拉脫維亞 立陶宛", 395, "VDR0000001843", 39600,
                        "2020-03-12", "2020-03-19", 25, 25);
                tourDao.insertTour(tour);
                tour = new Tour("【10人成行】就愛釜慶邱. 愛來水族館. 伽倻王國彩繪秀+星光庭院.甘川洞文化村五日", 342, "VDR0000001839", 23900,
                        "2020-06-25", "2020-06-29", 10, 16);
                tourDao.insertTour(tour);

            });
        }

    };
}
