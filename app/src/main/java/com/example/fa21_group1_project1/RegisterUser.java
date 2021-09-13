package com.example.fa21_group1_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterUser extends AppCompatActivity {
    Button registerBtn;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        AccountsDatabase db = Room.databaseBuilder(getApplicationContext(), AccountsDatabase.class, "accounts_table")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        AccountsDao accountsDao = db.AccountsDao();

        registerBtn = findViewById(R.id.registerNewButton);
        username = findViewById(R.id.fieldUsername);
        password = findViewById(R.id.fieldPassword);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = username.getText().toString().trim();
                String enteredPassword = password.getText().toString().trim();
                Accounts checkAccountExist = accountsDao.findTodoByUsername(enteredUsername);
                Accounts newAccount = validateNewUser(enteredUsername, enteredPassword, checkAccountExist);
                if (newAccount != null) {
                    accountsDao.insertAccount(newAccount);
                    registerSuccess();
                }
            }
        });
    }

    public void registerSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        Toast.makeText(this, "Register Successful!", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    static public boolean checkForValidUsername (String user, Accounts account, Context context) {
        if (user.isEmpty()) {
            Toast.makeText(context, "Your username can not be empty please enter a valid username! (5 characters or more)", Toast.LENGTH_LONG).show();
            return false;
        } else if (user.length() < 5) {
            Toast.makeText(context, "Your username is invalid please enter a valid username! (5 characters or more)", Toast.LENGTH_LONG).show();
            return false;
        } else if (account != null) {
            Toast.makeText(context, "Your username is already taken please enter a valid username! (5 characters or more)", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    static public boolean checkForValidPassword (String pass, Context context) {
        if (pass.isEmpty()) {
            Toast.makeText(context, "Your password can not be empty please enter a valid username! (5 characters or more)", Toast.LENGTH_LONG).show();
            return false;
        } else if (pass.length() < 5) {
            Toast.makeText(context, "Your password is invalid please enter a valid username! (5 characters or more)", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public Accounts validateNewUser(String enteredUsername, String enteredPassword, Accounts account) {
        boolean goodUsername, goodPassword;

        if (checkForValidUsername(enteredUsername, account, getApplicationContext())) {
            goodUsername = true;
        } else {
            goodUsername = false;
        }
        if (checkForValidPassword(enteredPassword, getApplicationContext())) {
            goodPassword = true;
        } else {
            Toast.makeText(getApplicationContext(), "Please enter a valid password! (5 characters or more)", Toast.LENGTH_LONG).show();
            goodPassword = false;
        }
        if (goodUsername && goodPassword) {
            Accounts newAccount = new Accounts(enteredUsername, enteredPassword);
            return newAccount;
        }
        return null;
    }
}