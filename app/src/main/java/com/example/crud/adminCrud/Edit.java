package com.example.crud.adminCrud;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.crud.R;
import com.example.crud.database.Util;
import com.example.crud.database.tables.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

public class Edit extends AppCompatActivity {

    private int id ,isAdmin;
    private String username, email, password;
    private Button btn_update;
    boolean input_empty = false;
    ArrayList<TextInputEditText> input_list = new ArrayList<TextInputEditText>();

    private TextInputEditText input_username, input_email, input_password, input_isAdmin;

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btn_update = findViewById(R.id.btn_signin);

        id = getIntent().getExtras().getInt("id");
        username = getIntent().getExtras().getString("username");
        email = getIntent().getExtras().getString("email");
        password = getIntent().getExtras().getString("password");
        isAdmin = getIntent().getExtras().getInt("isAdmin");

        input_username = findViewById(R.id.edit_input_username);
        input_email = findViewById(R.id.edit_input_email);
        input_password = findViewById(R.id.edit_input_password);
        input_isAdmin = findViewById(R.id.signin_input_password);

        input_username.setText(username);
        input_email.setText(email);
        input_password.setText(password);
        input_isAdmin.setText(String.valueOf(isAdmin));

        input_list.add(input_username);
        input_list.add(input_email);
        input_list.add(input_password);
        input_list.add(input_isAdmin);

        btn_update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                User user = new User(Edit.this);
                user.setId(id);
                user.setUsername(input_username.getText().toString().trim());
                user.setEmail(input_email.getText().toString().trim());
                user.setPassword(input_password.getText().toString().trim());
                user.setAdmin(Integer.parseInt(input_isAdmin.getText().toString().trim()));

                for (TextInputEditText item : input_list) {
                    if(item.getText().toString().equals("")){
                        input_empty = true;
                        Toast.makeText(Edit.this, "Hay campos vacíos", Toast.LENGTH_SHORT).show();
                    }
                }

                if(!input_empty){
                    if(user.update()){
                        Intent intent = new Intent(Edit.this, AdminActivity.class);
                        startActivity(intent);
                        Toast.makeText(Edit.this, "Update! -> new username: "+user.getUsername(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    input_empty = false;
                }
            }
        });
    }
}