package com.example.fa21_group1_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button loginBtn, registerBtn, viewAccountsBtn;
    EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AccountsDatabase db = Room.databaseBuilder(getApplicationContext(), AccountsDatabase.class, "accounts_table")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        AccountsDao accountsDao = db.AccountsDao();

        loginBtn = findViewById(R.id.loginButton);
        registerBtn = findViewById(R.id.registerButton);
        viewAccountsBtn = findViewById(R.id.showAccountsButton);
        username = findViewById(R.id.fieldUsername);
        password = findViewById(R.id.fieldPassword);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();
                Accounts checkAccountExist = accountsDao.findTodoByCredentials(enteredUsername, enteredPassword);

                if (validateUser(checkAccountExist)) {
                    loginSuccess();
                }
                else {
                    loginFailure();
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        viewAccountsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAccounts();
            }
        });

    }

    // temporary method in which we will login until we work out Room Databases likely will need getUsers()
    static public boolean validateUser (Accounts account) {
        if (account != null) {
            return true;
        }
        return false;
    }

    public void registerUser() {
        Intent intent = new Intent(this, RegisterUser.class);
        intent.putExtra("testSwitch", true);
        startActivity(intent);
    }

    public void viewAccounts() {
        Intent intent = new Intent(this, ViewAccounts.class);
        startActivity(intent);
    }

    public void loginSuccess() {
        Intent intent = new Intent(this, PictureSearch.class);
        Toast.makeText(this, "Login Successful!", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    public void loginFailure() {
        Toast.makeText(this, "Login Failed!", Toast.LENGTH_LONG).show();
    }
}