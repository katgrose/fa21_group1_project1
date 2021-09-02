package com.example.fa21_group1_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button loginBtn, registerBtn;
    EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.loginButton);
        registerBtn = findViewById(R.id.registerButton);
        username = findViewById(R.id.fieldUsername);
        password = findViewById(R.id.fieldPassword);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateUser(username.getText().toString(), password.getText().toString())) {
                    loginSuccess();
                }
                else {
                    loginFailure();
                }
            }
        });
    }

    // temporary method in which we will login until we work out Room Databases likely will need getUsers()
    static public boolean validateUser (String user, String pass) {
        if (user.equals("admin") && pass.equals("admin")) {
            return true;
        }
        return false;
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