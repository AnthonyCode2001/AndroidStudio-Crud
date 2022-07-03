package com.example.crud.login;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crud.R;
import com.example.crud.database.tables.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class SignIn extends AppCompatActivity {

    Button btn_signin;
    TextView btn_login;
    TextInputEditText input_username, input_email, input_password;
    ArrayList<TextInputEditText> input_list = new ArrayList<TextInputEditText>();
    boolean input_empty = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btn_signin = findViewById(R.id.btn_signin);
        btn_login = findViewById(R.id.signin_btn_login);

        input_username = findViewById(R.id.signin_input_username);
        input_email = findViewById(R.id.signin_input_email);
        input_password = findViewById(R.id.signin_input_password);

        input_list.add(input_username);
        input_list.add(input_email);
        input_list.add(input_password);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View view) {
                for(TextInputEditText item: input_list){
                    if(item.getText().toString().equals("")){
                        input_empty = true;
                        Toast.makeText(SignIn.this, "Hay campos vacíos", Toast.LENGTH_SHORT).show();
                    }
                }

                if(!input_empty){
                    User user = new User(SignIn.this);
                    user.setUsername(input_username.getText().toString().trim());
                    user.setEmail(input_email.getText().toString().trim());
                    user.setPassword(input_password.getText().toString().trim());

                    if(user.create()){
                        Toast.makeText(SignIn.this, "Nuevo usuario!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    input_empty = false;
                }
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, Login.class);
                startActivity(intent);
            }
        });
    }
}