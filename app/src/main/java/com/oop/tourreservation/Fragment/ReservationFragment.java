package com.oop.tourreservation.Fragment;

import com.oop.tourreservation.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ReservationFragment extends Fragment {
    @Nullable
    @Override
    // inflate reservationFragment
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.reservation_fragment, container, false);
    }
}
