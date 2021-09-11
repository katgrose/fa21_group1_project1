package com.example.fa21_group1_project1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Accounts.class}, version = 2)
public abstract class AccountsDatabase extends RoomDatabase {

    public abstract AccountsDao AccountsDao();

    private static volatile AccountsDatabase INSTANCE;

    static AccountsDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AccountsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AccountsDatabase.class, "accounts_table")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}