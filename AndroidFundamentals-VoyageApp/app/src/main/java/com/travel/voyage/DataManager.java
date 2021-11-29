package com.travel.voyage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.travel.voyage.room.User;

/**
 *
 * @author Beti
 */

public abstract class DataManager {

    // ATTRIBUTES
    public final static String LOGGED_IN_USER = "LOGGED_IN_USER"; // constant for logged in user

    public final static String EDIT_TRIP_ID   = "EDIT_TRIP_ID";   // constant for edit trip id

    public final static String VIEW_TRIP_ID   = "VIEW_TRIP_ID";   // constant for view trip id

    public final static String USERS_DB_NAME  = "users.db";       // constant for users database name

    public final static String TRIPS_DB_NAME  = "trips.db";       // constant for trips database name

    private static User loggedInUser;


    // METHODS

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();

        if (loggedInUser != null) {
            String userJson = gson.toJson(loggedInUser);
            editor.putString(DataManager.LOGGED_IN_USER, userJson);
        } else {
            editor.putString(DataManager.LOGGED_IN_USER, null);
        }

        editor.apply();

        if (loggedInUser != null) {
            DataManager.loggedInUser = gson.fromJson(preferences.getString(DataManager.LOGGED_IN_USER, ""), User.class);
        } else {
            DataManager.loggedInUser = null;
        }
    }
}
