package com.example.fa21_group1_project1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.fa21_group1_project1.data.User;
import com.example.fa21_group1_project1.data.UserDao;
import com.example.fa21_group1_project1.data.UserDatabase;

import java.util.List;
import java.util.Observable;

public class ViewAccounts extends AppCompatActivity {
    private AccountsAdapter accountsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_accounts);


//        //TextView viewAccountsTV = findViewById(R.id.viewAccountsTV);
//
//
//        UserDatabase db = UserDatabase.Companion.getDatabase(this);
//
//        final Observer<LiveData<List<User>>> observer = new Observer<LiveData<List<User>>>() {
//            @Override
//            public void onChanged(LiveData<List<User>> listLiveData) {
//                // Update UI
//                //viewAccountsTV.setText((CharSequence) listLiveData.getValue());
//            }
//        };
        initRecyclerView();
        loadUserList();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerviewAccounts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        accountsAdapter = new AccountsAdapter(this);
        recyclerView.setAdapter(accountsAdapter);
    }

    private void loadUserList(){
        AccountsDatabase db = Room.databaseBuilder(getApplicationContext(), AccountsDatabase.class, "accounts_table")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        AccountsDao accountsDao = db.AccountsDao();
        //UserDatabase db = UserDatabase.Companion.getDatabase(this.getApplicationContext());
        LiveData<List<Accounts>> users = accountsDao.readAllData();
        accountsAdapter.setUserList(users);

    }

    public static Intent getIntent(Context context, String toastValue){
        Intent intent = new Intent(context, ViewAccounts.class);
        return intent;
    }
}
