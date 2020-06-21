package com.oop.tourreservation.Fragment;

import com.oop.tourreservation.AppViewModel;
import com.oop.tourreservation.Entity.*;
import com.oop.tourreservation.MainActivity;
import com.oop.tourreservation.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class OrderFragment extends Fragment {

    private Order order;
    private AppViewModel viewModel;

    public OrderFragment(Order order) { this.order = order; }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class); // access viewModel

        // get user and tour
        User user = viewModel.getUser(order.user_id);
        Tour tour = viewModel.getTour(order.tour_id);

        // get info from user and tour
        String tourName = tour.title;
        String username = "帳號: " + user.username;
        String orderId = "訂單編號: " + order.id;
        String date = MainActivity.dateToString(tour.start_date) + " ~ " + MainActivity.dateToString(tour.end_date);
        String adult = "成人: " + order.adult_num;
        String child = "孩童: " + order.child_num;
        String baby = "嬰兒: " + order.baby_num;
        String price = "總價: " + order.total_price;

        // access views
        TextView tv_tourName = view.findViewById(R.id.of_tourname);
        TextView tv_username = view.findViewById(R.id.of_username);
        TextView tv_orderId = view.findViewById(R.id.of_orderid);
        TextView tv_date = view.findViewById(R.id.of_date);
        TextView tv_adult = view.findViewById(R.id.of_adult);
        TextView tv_child = view.findViewById(R.id.of_child);
        TextView tv_baby = view.findViewById(R.id.of_baby);
        TextView tv_price = view.findViewById(R.id.of_price);

        // set views using info
        tv_tourName.setText(tourName);
        tv_username.setText(username);
        tv_orderId.setText(orderId);
        tv_date.setText(date);
        tv_adult.setText(adult);
        tv_child.setText(child);
        tv_baby.setText(baby);
        tv_price.setText(price);


    }
}
