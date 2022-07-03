package com.example.crud.login;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.crud.R;
import com.example.crud.adminCrud.AdminActivity;
import com.example.crud.database.Util;
import com.example.crud.database.tables.User;
import com.example.crud.videoRecorder.VideoRecorder;
import com.example.crud.videoRecorder.VideoRecorder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Login extends AppCompatActivity {

    Button btn_login;
    private EditText email;
    private EditText password;
    private TextView btn_signin;
    Map<String, Integer> datesUtil;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(ContextCompat.getColor(Login.this, R.color.white));

        email = findViewById(R.id.login_input_email);
        password = findViewById(R.id.login_input_password);
        btn_login = findViewById(R.id.btn_login);
        btn_signin = findViewById(R.id.login_btn_signin);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignIn.class);
                startActivity(intent);
            }
        });

        //radiactive IM
        btn_login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                datesUtil = new HashMap<String, Integer>();

                if(!email.getText().toString().trim().equals("") && !password.getText().toString().trim().equals("")){
                    if(verifyUser()){
                        switch (datesUtil.get("isAdmin")){
                            case 0:
                                Intent intent = new Intent(Login.this, VideoRecorder.class);
                                intent.putExtra("id", datesUtil.get("id"));
                                startActivity(intent);
                                finish();
                                break;
                            case 1:
                                intent = new Intent(Login.this, AdminActivity.class);
                                intent.putExtra("id", datesUtil.get("id"));
                                startActivity(intent);
                                finish();
                                break;
                            default:
                                intent = new Intent(Login.this, VideoRecorder.class);
                                intent.putExtra("id", datesUtil.get("id"));
                                startActivity(intent);
                                finish();
                        }

                        Util.toast(Login.this, "Usuario encontrado! -> ID: "+verifyUser());
                    }else{
                        password.setText("");
                        Util.toast(Login.this, "El usuario no existe");
                    }
                }else{
                    Util.toast(Login.this, "Complete todos los campos");
                }

                User user = new User(Login.this);
                //onBackPressed();
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean verifyUser(){
        User user = new User(Login.this);
        AtomicBoolean exists = new AtomicBoolean(false);

        user.read().forEach(item -> {
            if(item.getEmail().compareToIgnoreCase(email.getText().toString().trim()) == 0){
                if(item.getPassword().equals(password.getText().toString())){
                    datesUtil.put("id", item.getId());
                    datesUtil.put("isAdmin", item.getAdmin());
                    exists.set(true);
                }
            }
        });
        return exists.get();
    }
}