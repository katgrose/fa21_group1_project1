package com.example.fa21_group1_project1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AccountsDao {

    @Insert
    void insertAccount(Accounts account);

    @Query("SELECT * FROM accounts_table ORDER BY uid ASC")
    LiveData<List<Accounts>> readAllData();

    @Query("SELECT * FROM accounts_table WHERE accounts_username LIKE :username")
    Accounts findTodoByUsername(String username);

    @Query("SELECT * FROM accounts_table WHERE accounts_username LIKE :username AND accounts_password LIKE :password")
    Accounts findTodoByCredentials(String username, String password);

    @Delete
    void deleteAccount(Accounts account);

}
