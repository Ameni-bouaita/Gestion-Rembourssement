package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {remboursement.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract remboursementDAO remboursementDAO();

}
