package com.example.crud.database;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.example.crud.adminCrud.Edit;

import java.util.Base64;

public class Util {
    Edit edit = new Edit();

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String cifrar(String valor){
        return Base64.getEncoder().encodeToString(valor.getBytes());
    }


    public static void muestra_dialogo(Activity activity, String Texto){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("informacion");
        builder.setMessage(Texto);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }


    public static void toast(Activity activity, String msg){
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }
}
