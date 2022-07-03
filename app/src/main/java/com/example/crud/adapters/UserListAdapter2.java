package com.example.crud.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.adminCrud.AdminActivity;
import com.example.crud.R;
import com.example.crud.adminCrud.Delete;
import com.example.crud.adminCrud.Edit;
import com.example.crud.database.tables.User;

import java.util.ArrayList;

public class UserListAdapter2 extends RecyclerView.Adapter<UserListAdapter2.ViewHolderDates> {
    boolean isDarkGrey = true;

    //private final Context context;
    private final ArrayList<User> userList;
    Activity activity;
    ArrayList<String> row;

    public UserListAdapter2(AdminActivity adminActivity, ArrayList<User> userList){
        this.userList = userList;
        this.activity = adminActivity;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public UserListAdapter2.ViewHolderDates onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_table_row, parent, false);
        return new ViewHolderDates(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDates holder, int position) {
        if(userList != null && userList.size() > 0){
           holder.setDates(userList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolderDates extends RecyclerView.ViewHolder {
        TextView id, username, email, password, isAdmin;
        TableRow tableRow;
        ImageButton btnEdit, btnDelete;

        public ViewHolderDates(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            username = itemView.findViewById(R.id.username);
            email = itemView.findViewById(R.id.email);
            password = itemView.findViewById(R.id.password);
            isAdmin = itemView.findViewById(R.id.isAdmin);
            tableRow = itemView.findViewById(R.id.userTable);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }

        @SuppressLint("ResourceAsColor")
        public void setDates(User user) {
            id.setText(String.valueOf(user.getId()));
            username.setText(user.getUsername());
            email.setText(user.getEmail());
            password.setText(user.getPassword());
            isAdmin.setText(String.valueOf(user.getAdmin()));
            tableRow.findViewById(R.id.userTable);

            btnEdit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Edit.class);
                    intent.putExtra("id", user.getId());
                    intent.putExtra("username", user.getUsername());
                    intent.putExtra("email", user.getEmail());
                    intent.putExtra("password", user.getPassword());
                    intent.putExtra("isAdmin", user.getAdmin());

                    activity.startActivity(intent);
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Delete.class);
                    intent.putExtra("id", user.getId());
                    intent.putExtra("email", user.getEmail());
                    activity.startActivity(intent);
                }
            });

            /*if (user.getId()%2!=0) {
                tableRow.setBackgroundColor(R.color.black);
                isDarkGrey = false;
            }else{
                tableRow.setBackgroundColor(R.color.white);
                isDarkGrey = true;
            }*/
        }
    }
}
