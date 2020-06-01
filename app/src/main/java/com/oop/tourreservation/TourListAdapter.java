package com.oop.tourreservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TourListAdapter extends RecyclerView.Adapter<TourListAdapter.TourViewHolder> {

    public class TourViewHolder extends RecyclerView.ViewHolder {
        public TextView tourName;
        public TextView price;
        public TextView minPeople;
        public TextView maxPeople;
        public TextView startDate;
        public TextView endDate;


        public TourViewHolder(@NonNull View itemView) {
            super(itemView);

            tourName = (TextView)itemView.findViewById(R.id.tour_name);
            price = (TextView)itemView.findViewById(R.id.price);
            minPeople = (TextView)itemView.findViewById(R.id.lower_bound);
            maxPeople = (TextView)itemView.findViewById(R.id.upper_bound);
            startDate = (TextView)itemView.findViewById(R.id.start_date);
            endDate = (TextView)itemView.findViewById(R.id.end_date);
        }
    }

    private List<Tour> mTours; // cached copy of words

    public TourListAdapter(List<Tour> tours) {
        mTours = tours;
    }

    @NonNull
    @Override
    public TourListAdapter.TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tourView = inflater.inflate(R.layout.tour_layout, parent, false);

        return new TourViewHolder(tourView);
    }

    @Override // populating data into the item through holder
    public void onBindViewHolder(@NonNull TourListAdapter.TourViewHolder holder, int position) {
        Tour tour = mTours.get(position);

        holder.tourName.setText(tour.title);
        holder.price.setText(tour.price);
        holder.minPeople.setText(tour.lower_bound);
        holder.maxPeople.setText(tour.upper_bound);
        holder.startDate.setText(tour.start_date.toString());
        holder.endDate.setText(tour.end_date.toString());
    }

    @Override
    public int getItemCount() {
        return mTours.size();
    }
}
