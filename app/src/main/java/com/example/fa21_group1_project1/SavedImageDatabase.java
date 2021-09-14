package com.example.fa21_group1_project1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {SavedImage.class, Accounts.class}, version = 1, exportSchema = false)
public abstract class SavedImageDatabase extends RoomDatabase {
    public abstract SavedImageDao SavedImageDao();

    private static volatile SavedImageDatabase INSTANCE;

    static SavedImageDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SavedImageDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SavedImageDatabase.class, "saved_images")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}