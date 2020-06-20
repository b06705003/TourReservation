package com.oop.tourreservation.Fragment;

import java.text.SimpleDateFormat;
import com.oop.tourreservation.AppViewModel;
import com.oop.tourreservation.Entity.*;
//import com.oop.tourreservation.ReservationDialogFragment;
import com.oop.tourreservation.R;
import com.oop.tourreservation.TourListAdapter;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SearchFragment extends Fragment implements TourListAdapter.OnTourClickListener {

    private AppViewModel viewModel;
    private List<Tour> tours;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);
        EditText et_destination = getView().findViewById(R.id.et_destination);
        EditText et_from = getView().findViewById(R.id.et_from);
        EditText et_to = getView().findViewById(R.id.et_to);
        Button btn_search = getView().findViewById(R.id.btn_search);

        // set first button
        et_from.setInputType(InputType.TYPE_NULL);
        et_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        et_from.setText(getString(R.string.date_format, year, month+1, day));
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        // set second button
        et_to.setInputType(InputType.TYPE_NULL);
        et_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        et_to.setText(getString(R.string.date_format, year, month+1, day));
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        btn_search.setOnClickListener((View v) -> {

            for (Integer code: viewModel.getAllCodes()) {
                Log.d("Test all codes", String.valueOf(code));
            }

            tours = new ArrayList<>();
            List<Integer> travelCodes = viewModel.getTravelCodesByString("%" + et_destination.getText().toString() + "%");

            Log.d("MainActivity", String.valueOf(travelCodes.size()));

            for (Integer code: travelCodes) {
                Date from = strToDate(et_from.getText().toString());
                Date to = strToDate(et_to.getText().toString());
                ///////////////////////////////////////////////////////
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//                Date curDate = new Date(System.currentTimeMillis()) ; // 獲取當前時間
//                String str = formatter.format(curDate);
                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String date = sDateFormat.format(new java.util.Date());
                Date now = strToDate(date);
                if(from.compareTo(now) < 0){
                    from = now;
                }
                if(to.compareTo(now) >= 0){
                    List<Tour> aList = viewModel.getToursByCodeAndDate(code, from, to, "price");
                    tours.addAll(aList);
                    Log.d(aList.toString(), "MainActivity:90");
                }

                //////////////////////////////////////////////////////
//                List<Tour> aList = viewModel.getToursByCodeAndDate(code, from, to, "price");
//                tours.addAll(aList);
//               Log.d(aList.toString(), "MainActivity:90");
            }

            // enabling recyclerview
            if (tours.size() == 0) { //
                Toast.makeText(getContext(), R.string.no_tours, Toast.LENGTH_LONG).show();
            } else {
                TourListAdapter adapter = new TourListAdapter(tours, this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

        });
    }

    private Date strToDate(String str) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            Log.d("ERROR", "strToDate");
        }
        return date;
    }

    @Override
    public void onTourClick(int position) {
        // tours.get(position);
        // Intent intent = new Intent(this, NewActivity.class);
        // startActivity(intent);

        Tour tour = tours.get(position);
        showReservationDialog(tour);
    }

    private void showReservationDialog(Tour tour) {
        ReservationDialogFragment fragment = ReservationDialogFragment.newInstance(tour);
        FragmentManager manager = getParentFragmentManager();
        fragment.show(manager, "dialog");
    }
}
