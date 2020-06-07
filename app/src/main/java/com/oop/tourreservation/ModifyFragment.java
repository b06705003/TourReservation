package com.oop.tourreservation;

import android.os.Bundle;
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

        viewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        adult_picker = view.findViewById(R.id.adult_picker);
        child_picker = view.findViewById(R.id.child_picker);
        baby_picker = view.findViewById(R.id.baby_picker);
        modify_btn = view.findViewById(R.id.modify_btn);

        adult_picker.setMinValue(0);
        adult_picker.setMaxValue(10);
        child_picker.setMinValue(0);
        child_picker.setMaxValue(10);
        baby_picker.setMinValue(0);
        baby_picker.setMaxValue(10);

        adult_picker.setValue(order.adult_num);
        child_picker.setValue(order.child_num);
        baby_picker.setValue(order.baby_num);

        modify_btn.setOnClickListener(new View.OnClickListener() {
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
                    int diff = totalNum - (order.getTotalNum());

                    if (tour.addTourists(diff)) {
                        order.modify(adultNum, childNum, babyNum, tour.totalPrice(adultNum, childNum, babyNum));
                        viewModel.updateOrder(order);

                        viewModel.updateTour(tour);
                    }
                    else { // remaining quota not enough
                        Toast.makeText(getContext(), R.string.not_enough, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}
