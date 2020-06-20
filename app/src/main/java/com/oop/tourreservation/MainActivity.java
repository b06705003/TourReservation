package com.oop.tourreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.oop.tourreservation.Fragment.CheckFragment;
import com.oop.tourreservation.Fragment.SearchFragment;
import com.oop.tourreservation.Fragment.SignupFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    AppViewModel viewModel;
    // ArrayList<Tour> tours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(AppViewModel.class);

        // get access to views

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);

        BottomNavigationView.OnNavigationItemSelectedListener navigationListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        Fragment fragment = null;

                        switch (menuItem.getItemId()) {
                            case R.id.page_signup:
                                fragment = new SignupFragment();
                                break;
                            case R.id.page_search:
                                fragment = new SearchFragment();
                                break;
                            //case R.id.page_reservation:
                              //  fragment = new ReservationFragment();
                              //  break;
                            //case R.id.page_modify:
                            //    fragment = new ModifyFragment();
                             //   break;
                            case R.id.page_check:
                                fragment = new CheckFragment();
                                break;
                        }

                        openFragment(fragment);

                        return true;
                    }
                };

        navigation.setOnNavigationItemSelectedListener(navigationListener);
    }



    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static String dateToString(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }



}
