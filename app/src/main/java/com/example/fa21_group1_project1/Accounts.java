package com.example.fa21_group1_project1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "accounts_table")
public class Accounts {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "accounts_uid")
    private int uid;

    @ColumnInfo(name = "accounts_username")
    private String username;

    @ColumnInfo(name = "accounts_password")
    private String pw;

    public Accounts(String username, String pw) {
        this.username = username;
        this.pw = pw;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "username='" + username + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }
}
