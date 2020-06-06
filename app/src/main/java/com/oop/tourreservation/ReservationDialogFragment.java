package com.oop.tourreservation;

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

    public static ReservationDialogFragment newInstance(Tour tour) {
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

        viewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        TextView tv_tour_info = view.findViewById(R.id.tv_tour_info);
        tv_tour_info.setText(mTour.toString());

        EditText et_username = view.findViewById(R.id.rf_et_username);
        NumberPicker adult_picker = view.findViewById(R.id.adult_picker);
        NumberPicker child_picker = view.findViewById(R.id.child_picker);
        NumberPicker baby_picker = view.findViewById(R.id.baby_picker);
        Button reservation_btn = view.findViewById(R.id.reservation_btn);

        adult_picker.setMinValue(0);
        adult_picker.setMaxValue(10);
        child_picker.setMinValue(0);
        child_picker.setMaxValue(10);
        baby_picker.setMinValue(0);
        baby_picker.setMaxValue(10);

        reservation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et_username.getText().toString();

                int adult_num = adult_picker.getValue();
                int child_num = child_picker.getValue();
                int baby_num = baby_picker.getValue();

                int totalPrice = mTour.totalPrice(adult_num, child_num, baby_num);
                int totalNum = adult_num + child_num + baby_num;

                boolean isEnough = mTour.addTourists(totalNum);

                if (totalNum <= 0) {
                    Toast.makeText(getContext(), R.string.zero_people, Toast.LENGTH_LONG).show();
                }
                else if (isEnough) {
                    viewModel.updateTour(mTour);
                    User user = viewModel.getUserByUsername(username);
                    Order order = null;
                    try {
                        order = new Order(user.id, mTour.id, adult_num, child_num, baby_num, totalPrice);
                        viewModel.insertOrder(order);
                    } catch (Exception e) {
                        Toast.makeText(getContext(), R.string.no_username, Toast.LENGTH_LONG).show();
                    }

                }
                else {
                    Toast.makeText(getContext(), R.string.not_enough, Toast.LENGTH_LONG).show();
                }

                for (Order anOrder: viewModel.getAllOrders()) {
                    Log.d("order", anOrder.toString());
                }
            }
        });
    }
}
