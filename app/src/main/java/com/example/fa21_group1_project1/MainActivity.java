package com.example.fa21_group1_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button loginBtn, registerBtn;
    EditText username, password;
    TextView fetchquote;
    TextView fetchauthor;
    RequestQueue mQueue;
    Button buttonParse;
    Button nasaAPOD;


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
        username = findViewById(R.id.fieldUsername);
        password = findViewById(R.id.fieldPassword);
        fetchquote = findViewById(R.id.quote);
        fetchauthor = findViewById(R.id.author);
        buttonParse = findViewById(R.id.quoteBtn);
        nasaAPOD = findViewById(R.id.nasaApodBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();
                Accounts checkAccountExist = accountsDao.findTodoByCredentials(enteredUsername, enteredPassword);

                if (validateUser(checkAccountExist)) {
                    loginSuccess(checkAccountExist.getUid());
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

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                jsonParse();
            }
        });

        nasaAPOD.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                nasaAPODdisplay();
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

    public void loginSuccess(int userID) {
        Intent intent = new Intent(this, PictureSearch.class);
        Toast.makeText(this, "Login Successful!", Toast.LENGTH_LONG).show();
        Bundle extraInfo = new Bundle();
        extraInfo.putInt("userID",userID);
        intent.putExtras(extraInfo);
        startActivity(intent);
    }

    public void loginFailure() {
        Toast.makeText(this, "Login Failed!", Toast.LENGTH_LONG).show();
    }

    private void jsonParse(){
        String url = "https://api.quotable.io/random";
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){
                        try{
                            String quote = response.getString("content");
                            String author = response.getString("author");
                            fetchquote.setText(quote);
                            fetchauthor.setText(author);
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        error.printStackTrace();
                    }
        });

        mQueue.add(objectRequest);
    }

    public void nasaAPODdisplay(){
        Intent intent = new Intent(this, NasaAPOD.class);
        startActivity(intent);
    }
}