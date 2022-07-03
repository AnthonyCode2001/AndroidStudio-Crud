package com.example.crud.database.tables;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.example.crud.database.MyHelperSqlLite;
import com.example.crud.database.Util;

import java.util.ArrayList;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private int  isAdmin;
    private Button btnEdit;
    MyHelperSqlLite conn;
    SQLiteDatabase db;

    public User(){}

    public User(Activity activity){
        conn = new MyHelperSqlLite(activity, "crud_android.db", null, 1);
        db = conn.getWritableDatabase();//SELECT, UPDATE, DELETE
    }

    public User(int id, String username, String email, String password, int isAdmin, Button btnEdit){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.btnEdit = btnEdit;
    }

    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

    public int getAdmin(){return isAdmin;}
    public void setAdmin(int isAdmin){this.isAdmin = isAdmin;}

    public Button getBtnEdit(){return btnEdit;}
    public void setBtnEdit(Button btnEdit){this.btnEdit = btnEdit;}


    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean create(){
        ContentValues row = new ContentValues();
        row.put("username", this.username);
        row.put("email", this.email);
        row.put("password", this.password);
        row.put("isAdmin", this.isAdmin);
        db.insert("users", null, row);
        db.close();
        return true;
    }

    public ArrayList<User> read(){
        ArrayList<User> list = new ArrayList<>();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select id, username, email, password, isAdmin from users", null);
        while(cursor.moveToNext()){
            User user = new User();
            user.id = cursor.getInt(0);
            user.username = cursor.getString(1);
            user.email = cursor.getString(2);
            user.password = cursor.getString(3);
            user.isAdmin = cursor.getInt(4);
            user.btnEdit = this.btnEdit;
            list.add(user);
        }
        db.close();
        return list;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean update(){
        ContentValues row = new ContentValues();
        row.put("username", this.username);
        row.put("email", this.email);
        row.put("password", this.password);
        row.put("isAdmin", this.isAdmin);
        db.update("users", row, "id="+this.id, null);
        db.close();
        return true;
    }

    public boolean delete(){
        db.delete("users", "id="+this.id, null);
        db.close();
        return true;
    }
}
