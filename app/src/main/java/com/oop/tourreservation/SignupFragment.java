package com.oop.tourreservation;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class SignupFragment extends Fragment {

    private AppViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.signup_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        // get reference to Views
        EditText et_realname = getView().findViewById(R.id.et_realname);
        EditText et_username = getView().findViewById(R.id.et_username);
        EditText et_password = getView().findViewById(R.id.et_password);
        Button signup_btn = getView().findViewById(R.id.signup_btn);
        TextView tv_info = getView().findViewById(R.id.tv_info);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String realname = et_realname.getText().toString();
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                User user = new User(username, password, realname);

                try {
                    viewModel.insertUser(user);
                    tv_info.setText(R.string.signup_success);

                } catch (SQLiteConstraintException e) {
                    tv_info.setText(R.string.same_username);
                }


                for (User aUser: viewModel.getAllUsers()) {
                    Log.d("onClick: ", aUser.toString());
                }
            }
        });
    }
}
