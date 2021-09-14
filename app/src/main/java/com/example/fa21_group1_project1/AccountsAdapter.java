package com.example.fa21_group1_project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fa21_group1_project1.data.User;

import java.util.List;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.ViewHolder> {

    private Context context;
    private LiveData<List<Accounts>> userList;

    //Constructor
    public AccountsAdapter(Context context){
        this.context = context;
    }

    public void setUserList(LiveData<List<Accounts>> userList){
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AccountsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.individual_account_entry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountsAdapter.ViewHolder holder, int position) {
        holder.userNameTV.setText(this.userList.getValue().indexOf(position));
        //Lastname other data here...
    }

    @Override
    public int getItemCount() {
        //return this.userList.getValue().size();
        return 0;
    }

//    public TextView userNameTextView;
//    private LiveData<List<User>> localDataset;
//
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTV;
        public ViewHolder(View view){
            super(view);
            userNameTV = view.findViewById(R.id.userName);
        }
    }
//
//    // Initialize dataset of the Adapter
//    public AccountsAdapter(LiveData<List<User>> dataset){
//        localDataset = dataset;
//    }
//


}
