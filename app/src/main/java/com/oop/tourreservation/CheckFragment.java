package com.oop.tourreservation;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.check_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        EditText et_username = view.findViewById(R.id.cf_et_username);
        EditText et_orderid = view.findViewById(R.id.cf_et_orderid);
        Button btn_check = view.findViewById(R.id.btn_check);

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et_username.getText().toString();
                int orderid = Integer.parseInt(et_orderid.getText().toString());

                try {
                    User user = viewModel.getUserByUsername(username);
                    Order order = viewModel.getOrder(orderid);

                    if (user.id != order.user_id)
                        throw new Exception();

                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.order_frame, new OrderFragment(order));
                    transaction.commit();

                } catch (Exception e) {
                    Toast.makeText(getContext(), R.string.no_such_order, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
