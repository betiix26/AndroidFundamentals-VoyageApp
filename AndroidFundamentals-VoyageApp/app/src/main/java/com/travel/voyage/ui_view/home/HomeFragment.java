package com.travel.voyage.ui_view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.travel.voyage.DataManager;
import com.travel.voyage.HomeActivity;
import com.travel.voyage.R;
import com.travel.voyage.room.Trip;
import com.travel.voyage.trip.TripAdapter;
import com.travel.voyage.room.TripDao;
import com.travel.voyage.room.TripDataBase;

import java.util.List;

/**
 * @author Beti
 */

public class HomeFragment extends Fragment {

    private List<Trip> trips;
    private RecyclerView recyclerViewTrips;

    private TripDataBase tripDataBase;
    private TripDao tripDao;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        HomeActivity.fab.show();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tripDataBase = Room.databaseBuilder(view.getContext(), TripDataBase.class, DataManager.TRIPS_DB_NAME).allowMainThreadQueries().build();
        tripDao = tripDataBase.getTripDao();

        trips = tripDao.getUserTrips(DataManager.getLoggedInUser().getId());

        recyclerViewTrips = getView().findViewById(R.id.recycler_view_trips);
        recyclerViewTrips.setLayoutManager(new LinearLayoutManager(getView().getContext()));

        recyclerViewTrips.setAdapter(new TripAdapter(trips));
    }
}