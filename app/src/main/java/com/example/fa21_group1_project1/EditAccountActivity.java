package com.example.fa21_group1_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class EditAccountActivity extends AppCompatActivity {

    EditText eUserName;
    EditText ePassword;
    Button bUpdateAccount;
    int passedUid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        //instantiate db
        AccountsDatabase db = Room.databaseBuilder(getApplicationContext(), AccountsDatabase.class, "accounts_table")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        AccountsDao accountsDao = db.AccountsDao();
        //pass in uid extras with bundle
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            passedUid = extras.getInt("userID");
       }

        Accounts newAccount = accountsDao.findTodoByUid(passedUid);
        eUserName = (EditText) findViewById(R.id.etUserName);
        ePassword = (EditText) findViewById(R.id.etPassword);
        String user = eUserName.getText().toString();
        String pw = ePassword.getText().toString();
        bUpdateAccount = (Button) findViewById(R.id.btnUpdateAccount);
        bUpdateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.matches("") && pw.matches("")) {
                    newAccount.setPw(ePassword.getText().toString().trim());
                    newAccount.setUsername(eUserName.getText().toString().trim());
                    accountsDao.updateAccount(newAccount);
                }

            }
        });


        Toast.makeText(EditAccountActivity.this, newAccount.getUid()+"\n"+newAccount.getPw()+"\n"+newAccount.getUsername(), Toast.LENGTH_SHORT).show();

    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, EditAccountActivity.class);

        //intent.putExtra(SavedImages.ACTIVITY_LABEL,toastValue);

        return intent;
    }

    public boolean etNotEmpty(String ed_text)
    {
        if(ed_text.isEmpty() || ed_text.length() == 0 || ed_text.equals("") || ed_text == null)
        {
            //EditText is empty
            return false;
        }
        else
        {
            //EditText is not empty
            return true;
        }
    };
}