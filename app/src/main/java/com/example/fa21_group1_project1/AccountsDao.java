package com.example.fa21_group1_project1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AccountsDao {

    @Insert
    void insertAccount(Accounts account);

    @Query("SELECT * FROM accounts_table WHERE accounts_username LIKE :username")
    Accounts findTodoByUsername(String username);

    @Query("SELECT * FROM accounts_table WHERE accounts_username LIKE :username AND :password")
    Accounts findTodoByCredentials(String username, String password);

    @Delete
    void deleteAccount(Accounts account);

}
