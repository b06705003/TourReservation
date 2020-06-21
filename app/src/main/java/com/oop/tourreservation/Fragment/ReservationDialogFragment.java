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
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

public class ReservationDialogFragment extends DialogFragment {

    private Tour mTour;
    private AppViewModel viewModel;

    public static ReservationDialogFragment newInstance(Tour tour) { // creating a ReservationDialog using a tour
        ReservationDialogFragment f = new ReservationDialogFragment();
        f.setTour(tour);
        return f;
    }

    public void setTour(Tour tour) {
        this.mTour = tour;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.reservation_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class); // access to viewModel


        TextView tv_tour_info = view.findViewById(R.id.tv_tour_info);
        tv_tour_info.setText(mTour.toString());
        // access views
        EditText et_username = view.findViewById(R.id.rf_et_username);
        NumberPicker adult_picker = view.findViewById(R.id.adult_picker);
        NumberPicker child_picker = view.findViewById(R.id.child_picker);
        NumberPicker baby_picker = view.findViewById(R.id.baby_picker);
        Button reservation_btn = view.findViewById(R.id.modify_btn);

        // set upper and lower bounds for an order
        adult_picker.setMinValue(0);
        adult_picker.setMaxValue(10);
        child_picker.setMinValue(0);
        child_picker.setMaxValue(10);
        baby_picker.setMinValue(0);
        baby_picker.setMaxValue(10);

        // setup button for reservation
        reservation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et_username.getText().toString();

                int adult_num = adult_picker.getValue();
                int child_num = child_picker.getValue();
                int baby_num = baby_picker.getValue();

                int totalPrice = mTour.totalPrice(adult_num, child_num, baby_num);
                int totalNum = adult_num + child_num + baby_num;

                if (totalNum <= 0) { // at least one tourist to make an order
                    Toast.makeText(getContext(), R.string.zero_people, Toast.LENGTH_LONG).show();
                }
                else { // totalNum > 0
                    User user = viewModel.getUserByUsername(username);
                    Order order = null;
                    try {
                        order = new Order(user.id, mTour.id, adult_num, child_num, baby_num, totalPrice);

                        boolean isEnough = mTour.addTourists(totalNum);
                        if (isEnough) { // successful order
                            long order_id = viewModel.insertOrder(order);
                            viewModel.updateTour(mTour);
                            Toast.makeText(getContext(), R.string.successful_order , Toast.LENGTH_SHORT).show();
                            Toast.makeText(getContext(), "訂單編號：" + String.valueOf(order_id) + ", 預定人數" +  String.valueOf(order.getTotalNum())+", 總價格：" + String.valueOf(order.total_price), Toast.LENGTH_LONG).show();
                           // Toast.makeText(getContext(), "總價格：" + String.valueOf(order.total_price), Toast.LENGTH_LONG).show();
                        } else { // over upper bound of tour
                            Toast.makeText(getContext(), R.string.not_enough, Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) { // user doesn't exist, please signup first
                        Toast.makeText(getContext(), R.string.no_username, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
