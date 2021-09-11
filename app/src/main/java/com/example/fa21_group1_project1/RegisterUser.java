package com.example.fa21_group1_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

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
                accountsDao.insertAccount(validateNewUser(enteredUsername, enteredPassword, checkAccountExist));
            }
        });
    }

    static public boolean checkForValidUsername (String user, Accounts account) {
        if (user.isEmpty()) {
            return false;
        } else if (user.length() < 5) {
            return false;
        } else if (account != null) {
            return false;
        }
        return true;
    }

    static public boolean checkForValidPassword (String pass) {
        if (pass.isEmpty()) {
            return false;
        } else if (pass.length() < 5) {
            return false;
        }
        return true;
    }

    public Accounts validateNewUser(String enteredUsername, String enteredPassword, Accounts account) {
        boolean goodUsername, goodPassword;

        if (checkForValidUsername(enteredUsername, account)) {
            goodUsername = true;
        }
        else {
            Toast.makeText(getApplicationContext(), "Please enter a valid username! (5 characters or more)", Toast.LENGTH_LONG).show();
            goodUsername = false;
        }

        if (checkForValidPassword(enteredPassword)) {
            goodPassword = true;
        }
        else {
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