package com.travel.voyage.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
/**
 * @author Beti
 */
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {
    public abstract UserDao getUserDao();
}