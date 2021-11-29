package com.travel.voyage.trip;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.travel.voyage.R;
import com.travel.voyage.room.Trip;

import java.util.List;
/**
 * @author Beti
 */
public class TripAdapter extends RecyclerView.Adapter<TripViewHolder> {
    private final List<Trip> trips;

    public TripAdapter(List<Trip> trips) {
        this.trips = trips;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new TripViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        Trip currentTrip = trips.get(position);
        holder.setTripId(currentTrip.getId());

        if (currentTrip.isFavorite()) {
            holder.getButtonFavorite().setImageResource(R.drawable.ic_baseline_star_24);
        }

        switch(currentTrip.getType()){
            case 0:
                holder.getTripPicture().setImageResource(R.drawable.citysunset);
                break;
            case 1:
                holder.getTripPicture().setImageResource(R.drawable.seasunset);
                break;
            case 2:
                holder.getTripPicture().setImageResource(R.drawable.munti);
                break;
            default: holder.getTripPicture().setImageResource(R.drawable.munte);
        }

        holder.setFavorite(currentTrip.isFavorite());
        holder.getTextViewName().setText(currentTrip.getName());
        holder.getTextViewDestination().setText(currentTrip.getDestination());
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }
}
