package com.oop.tourreservation.Fragment;

import com.oop.tourreservation.AppViewModel;
import com.oop.tourreservation.Entity.*;
import com.oop.tourreservation.R;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModifyFragment extends Fragment {

    private Order order;
    private NumberPicker adult_picker;
    private NumberPicker child_picker;
    private NumberPicker baby_picker;
    private Button modify_btn;
    private AppViewModel viewModel;

    public ModifyFragment(Order order) {
        super();
        this.order = order;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.modify_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class); // get access to viewModel

        // get access to views
        adult_picker = view.findViewById(R.id.adult_picker);
        child_picker = view.findViewById(R.id.child_picker);
        baby_picker = view.findViewById(R.id.baby_picker);
        modify_btn = view.findViewById(R.id.modify_btn);

        // set lower and upper bound for an order
        adult_picker.setMinValue(0);
        adult_picker.setMaxValue(10);
        child_picker.setMinValue(0);
        child_picker.setMaxValue(10);
        baby_picker.setMinValue(0);
        baby_picker.setMaxValue(10);

        adult_picker.setValue(order.adult_num);
        child_picker.setValue(order.child_num);
        baby_picker.setValue(order.baby_num);

        modify_btn.setOnClickListener(new View.OnClickListener() { // setup button for modifying an order
            @Override
            public void onClick(View view) {
                int adultNum = adult_picker.getValue();
                int childNum = child_picker.getValue();
                int babyNum = baby_picker.getValue();
                int totalNum = adultNum + childNum + babyNum;

                if (totalNum == 0) {
                    Toast.makeText(getContext(), R.string.zero_people, Toast.LENGTH_LONG).show();
                }
                else { // totalNum > 0
                    Tour tour = viewModel.getTour(order.tour_id);
                    Date start = tour.start_date;

                    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String date = sDateFormat.format(new java.util.Date());
                    Date now = strToDate(date);



                    int diff = totalNum - (order.getTotalNum());

                    if(now.compareTo(start) >= 0){ // too late to modify
                        Toast.makeText(getContext(), R.string.fail_modify, Toast.LENGTH_SHORT).show();
                    }
                    else if (tour.addTourists(diff)) { // successfull modification
                        order.modify(adultNum, childNum, babyNum, tour.totalPrice(adultNum, childNum, babyNum));
                        viewModel.updateOrder(order);

                        viewModel.updateTour(tour);
                        Toast.makeText(getContext(), R.string.successful_modify, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), "修改後人數：大人" + String.valueOf(adultNum)+ ", 小孩:" + String.valueOf(childNum) + ", 嬰兒:" + String.valueOf(babyNum) , Toast.LENGTH_LONG).show();

                    }
                    else { // remaining quota not enough
                        Toast.makeText(getContext(), R.string.not_enough, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
    private Date strToDate(String str) { // convert from string to date
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            Log.d("ERROR", "strToDate");
        }
        return date;
    }
}
