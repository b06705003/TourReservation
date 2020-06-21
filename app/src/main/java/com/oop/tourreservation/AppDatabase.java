package com.oop.tourreservation;

import com.oop.tourreservation.Dao.*;
import com.oop.tourreservation.Entity.*;
import android.content.Context;
import android.content.res.AssetManager;
import android.net.ParseException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.simple.*;
import org.json.simple.parser.*;
import java.sql.SQLException;

import java.sql.*;
import java.io.*;
import org.json.simple.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Tour.class, Order.class, User.class, TravelCode.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract TourDao tourDao();
    public abstract OrderDao orderDao();
    public abstract UserDao userDao();
    public abstract TravelCodeDao travelCodeDao();

    public static JSONArray a; // for
    public static JSONArray b; //
    public static String title;
    public static String travel_code;
    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);



    static AppDatabase getDatabase(final Context context) { // get database instance
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "09")
                            .allowMainThreadQueries()
                            .addCallback(callback)
                            .build();
                }
            }
        }
       /*read data to SQLite*/
        /* change json to string to JSONArray*/
        if (INSTANCE != null) {
            TravelCode code;
            Tour tour;

            String fileName = "travel_code.json";
            StringBuilder stringBuilder = new StringBuilder();
            AssetManager assetManager = context.getAssets();

            try {
                JSONParser parser = new JSONParser();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(fileName), "utf-8"));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                String get_all = stringBuilder.toString();
                a = new JSONArray(get_all);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            fileName = "trip_data_all_1.json";
            stringBuilder = new StringBuilder();
            assetManager = context.getAssets();
            try {
                JSONParser parser = new JSONParser();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(fileName), "utf-8"));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                String get_all = stringBuilder.toString();
                b = new JSONArray(get_all);

                // since it will be something wrong when use "title" and "travel_code" as key directly
                // get key from methods keys() and store at title and travel_code

                Iterator keys = b.getJSONObject(0).keys();
                title = (String)keys.next();
                travel_code = (String) keys.next();

            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // Method is only called when the database is first generated
            // read the data into database
            databaseWriteExecutor.execute(() -> {
                TourDao tourDao = INSTANCE.tourDao();
                TravelCodeDao codeDao = INSTANCE.travelCodeDao();

                TravelCode code;
                Tour tour;
                int a_id = 0;
                String a_travel_code = "";

                try{
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject travel_code = a.getJSONObject(i);
                        a_id = Integer.valueOf(travel_code.getString("travel_code"));
                        a_travel_code = travel_code.getString("travel_code_name");

                        // create new travel_code and put into room database
                        code = new TravelCode(a_id, a_travel_code);
                        codeDao.insertTravelCode(code);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String b_title = "";
                int b_travel_code = 0;
                String b_product_key = "";
                int b_price = 0;
                String b_start_str = "";
                String b_end_str = "";
                int b_lower_bound = 0;
                int b_upper_bound = 0;

                try{
                    for (int i = 0; i < b.length(); i++) {
                        JSONObject travel = b.getJSONObject(i);

                        b_title = travel.getString(title);
                        b_travel_code = Integer.valueOf(travel.getString(travel_code));
                        b_product_key = travel.getString("product_key");
                        b_price = Integer.valueOf(travel.getString("price"));
                        b_start_str = travel.getString("start_date");
                        b_end_str = travel.getString("end_date");
                        b_lower_bound = Integer.valueOf(travel.getString("lower_bound"));
                        b_upper_bound = Integer.valueOf(travel.getString("upper_bound"));

                        // create new tour and put into room database
                        tour = new Tour(b_title, b_travel_code, b_product_key, b_price, b_start_str, b_end_str ,b_lower_bound, b_upper_bound);
                        tourDao.insertTour(tour);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("1111", "data_input_finish");
            });
        }
    };
}
