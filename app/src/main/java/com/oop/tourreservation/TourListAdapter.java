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

    private List<Tour> mTours; // cached copy of words
    private OnTourClickListener mOnTourClickListener;

    public TourListAdapter(List<Tour> tours, OnTourClickListener onTourClickListener) {
        this.mTours = tours;
        this.mOnTourClickListener = onTourClickListener;
    }

    public class TourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tourName;
        public TextView price;
        public TextView minPeople;
        public TextView maxPeople;
        public TextView startDate;
        public TextView endDate;
        OnTourClickListener onTourClickListener;


        public TourViewHolder(@NonNull View itemView, OnTourClickListener onTourClickListener) {
            super(itemView);

            tourName = (TextView)itemView.findViewById(R.id.of_tourname);
            price = (TextView)itemView.findViewById(R.id.price);
            minPeople = (TextView)itemView.findViewById(R.id.lower_bound);
            maxPeople = (TextView)itemView.findViewById(R.id.upper_bound);
            startDate = (TextView)itemView.findViewById(R.id.start_date);
            endDate = (TextView)itemView.findViewById(R.id.end_date);

            this.onTourClickListener = onTourClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTourClickListener.onTourClick(getAdapterPosition());
        }
    }



    @NonNull
    @Override
    public TourListAdapter.TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tourView = inflater.inflate(R.layout.tour_layout, parent, false);

        return new TourViewHolder(tourView, mOnTourClickListener);
    }

    @Override // populating data into the item through holder
    public void onBindViewHolder(@NonNull TourListAdapter.TourViewHolder holder, int position) {
        Tour tour = mTours.get(position);

        holder.tourName.setText(tour.title);
        holder.price.setText(String.valueOf(tour.price));
        holder.minPeople.setText(String.valueOf(tour.lower_bound));
        holder.maxPeople.setText(String.valueOf(tour.upper_bound));
        holder.startDate.setText(tour.start_date.toString());
        holder.endDate.setText(tour.end_date.toString());
    }

    @Override
    public int getItemCount() {
        return mTours.size();
    }

    public interface OnTourClickListener {
        void onTourClick(int position);
    }
}
