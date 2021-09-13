package com.example.fa21_group1_project1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {SavedImage.class}, version = 1)
public abstract class SavedImageDatabase extends RoomDatabase {
    public abstract SavedImagesDao SavedImagesDao();

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
