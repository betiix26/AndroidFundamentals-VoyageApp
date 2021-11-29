package com.travel.voyage.ui_view.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.travel.voyage.HomeActivity;
import com.travel.voyage.R;

/**
 * @author Beti
 */

public class AboutFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container, false);

        HomeActivity.fab.hide();

        return root;
    }
}