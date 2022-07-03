package com.example.crud.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.database.tables.User;

import java.util.ArrayList;

public class UserListAdapter{
    protected Activity activity;
    protected ArrayList<User> items;

    public UserListAdapter(Activity activity, ArrayList<User> items) {
        this.activity = activity;
        this.items = items;
    }

    public int getCount() {
        return items.size();
    }

    public Object getItem(int i) {
        return items.get(i);
    }

    public long getItemId(int i) {
        return i;
    }


    @SuppressLint({"ViewHolder", "InflateParams", "SetTextI18n"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v =view;
        LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE );
        v = inf.inflate(R.layout.model_table_row, null);

        User obj =  items.get(i);

        TextView lblId = v.findViewById(R.id.id);
        TextView lblUsername = v.findViewById(R.id.username);
        TextView lblEmail = v.findViewById(R.id.email);
        TextView lblPassword = v.findViewById(R.id.password);
        TextView lblIsAdmin = v.findViewById(R.id.isAdmin);

       lblId.setText("" + obj.getId());
        lblUsername.setText(obj.getUsername());
        lblEmail.setText(obj.getEmail());
        lblPassword.setText(obj.getPassword());
        //lblIsAdmin.setText(obj.getAdmin());

        return v;
    }
}
