package com.example.crud.adminCrud;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.crud.R;
import com.example.crud.adapters.UserListAdapter2;
import com.example.crud.database.tables.User;
import com.example.crud.login.SignIn;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<User> userList = new ArrayList<User>();
    Button btn_addUser;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        recyclerView = findViewById(R.id.recyclerRow);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        btn_addUser = findViewById(R.id.btn_addUser);
        show();

        btn_addUser.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, SignIn.class);
                startActivity(intent);
                /*User user = new User(AdminActivity.this);

                user.setId(1);
                user.setUsername("Uriel");
                user.setEmail("@gmail");
                user.setPassword("12345");
                user.setAdmin(0);
                if(user.create()){
                    Toast.makeText(AdminActivity.this, "Registro "+user.getId()+" guardado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AdminActivity.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                }*/

                show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void show(){
        User user = new User(AdminActivity.this);
        userList = user.read();

        UserListAdapter2 adapter = new UserListAdapter2(this, userList);
        recyclerView.setAdapter(adapter);

        /*userList.get(0).getBtnEdit().setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, Edit.class);
                startActivity(intent);
            }
        });*/

        //Util util = new Util();
        //util.buttonEdit(userList, AdminActivity.this);

    }
}