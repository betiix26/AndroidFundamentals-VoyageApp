package com.travel.voyage.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
/**
 * @author Beti
 */
@Database(entities = {Trip.class}, version = 1, exportSchema = false)
public abstract class TripDataBase extends RoomDatabase {
    public abstract TripDao getTripDao();
}