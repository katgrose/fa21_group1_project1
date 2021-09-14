package com.example.fa21_group1_project1;

import org.junit.Test;

import static org.junit.Assert.*;
import androidx.room.Room;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    AccountsDatabase db = Room.databaseBuilder(getApplicationContext(), AccountsDatabase.class, "accounts_table")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build();
    AccountsDao accountsDao = db.AccountsDao();

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testLogin(){
        Accounts accountTest = new Accounts("test1", "test2");
        accountsDao.insertAccount(accountTest);
        Accounts checkAccountExistTest = accountsDao.findTodoByCredentials("test1", "test2");
        assertTrue(MainActivity.validateUser(checkAccountExistTest));
    }


}