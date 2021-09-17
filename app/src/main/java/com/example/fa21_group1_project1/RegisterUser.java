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

    static public int checkForValidUsername (String user, Accounts account) {
        if (user.isEmpty()) {
            return 1;
        } else if (user.length() < 5) {
            return 2;
        } else if (account != null) {
            return 3;
        }
        return 0;
    }


    static public int checkForValidPassword (String pass) {
        if (pass.isEmpty()) {
            return 1;
        } else if (pass.length() < 5) {
            return 2;
        }
        return 0;
    }

    public Accounts validateNewUser(String enteredUsername, String enteredPassword, Accounts account) {
        boolean goodUsername, goodPassword;

        switch(checkForValidUsername(enteredUsername, account)) {
            case 0:
                goodUsername = true;
                break;
            case 1:
                goodUsername = false;
                errorMessage("Username can not be empty! Must be at least 5 characters long!", username);
                break;
            case 2:
                goodUsername = false;
                errorMessage("Username can not be under 5 characters! Must be at least 5 characters long!", username);
                break;
            case 3:
                goodUsername = false;
                errorMessage("Username already taken!", username);
                break;
            default:
                goodUsername = false;
                errorMessage("Error has occurred", username);
                break;
        }

        switch(checkForValidPassword(enteredPassword)) {
            case 0:
                goodPassword = true;
                break;
            case 1:
                goodPassword = false;
                errorMessage("Password can not be empty! Must be at least 5 characters long!", password);
                break;
            case 2:
                goodPassword = false;
                errorMessage("Password can not be under 5 characters! Must be at least 5 characters long!", password);
                break;
            default:
                goodPassword = false;
                errorMessage("Error has occurred", password);
                break;
        }

        if (goodUsername && goodPassword) {
            Accounts newAccount = new Accounts(enteredUsername, enteredPassword);
            return newAccount;
        }
        return null;
    }

    public void errorMessage(String msg, EditText field) {
        field.setError(msg);
    }
}