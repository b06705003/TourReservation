package com.oop.tourreservation.Fragment;

import com.oop.tourreservation.AppViewModel;
import com.oop.tourreservation.Entity.*;
import com.oop.tourreservation.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

public class CheckFragment extends Fragment {

    private AppViewModel viewModel;
    private EditText et_username;
    private EditText et_orderid;
    private Button btn_check;
    private Button btn_modify;
    private Button btn_cancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.check_fragment, container, false); // inflate the layout
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class); // get access to viewModel

        // get access to views
        et_username = view.findViewById(R.id.cf_et_username);
        et_orderid = view.findViewById(R.id.cf_et_orderid);
        btn_check = view.findViewById(R.id.btn_check);
        btn_modify = view.findViewById(R.id.btn_modify);
        btn_cancel = view.findViewById(R.id.btn_cancel);

        btn_check.setOnClickListener(new View.OnClickListener() { // setup button for checking an order
            @Override
            public void onClick(View view) {
                Order order = verifyOrder();
                if (order != null) {
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.order_frame, new OrderFragment(order));
                    transaction.commit();
                }
            }
        });

        btn_modify.setOnClickListener(new View.OnClickListener() { // setup button for modifying an order
            @Override
            public void onClick(View view) {
                Order order = verifyOrder();
                if (order != null) {
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.order_frame, new ModifyFragment(order));
                    transaction.commit();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() { // setup button for canceling an order
            @Override
            public void onClick(View view) {
                Order order = verifyOrder();
                if (order != null) {
                    viewModel.deleteOrder(order);
                    Toast.makeText(getContext(), R.string.cancel_success, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private Order verifyOrder() { // verify an order: whether exist or not
        String username = et_username.getText().toString();
        int orderid = 0;
        try {
            orderid = Integer.parseInt(et_orderid.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getContext(), R.string.no_such_order, Toast.LENGTH_LONG).show();
            return null;
        }
        User user = viewModel.getUserByUsername(username);
        Order order = viewModel.getOrder(orderid);
        try {
            if (user.id != order.user_id)
                throw new Exception();
        } catch (Exception e) {
            Toast.makeText(getContext(), R.string.no_such_order, Toast.LENGTH_LONG).show();
            return null;
        }
        return order;
    }
}
