package com.example.crud.adminCrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.crud.R;
import com.example.crud.database.tables.User;

public class Delete extends AppCompatActivity {
    private int id;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        id = getIntent().getExtras().getInt("id");
        email = getIntent().getExtras().getString("email");

        User user = new User(Delete.this);
        user.setId(id);

        if(email.compareToIgnoreCase(email) != 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(Delete.this);
            builder.setMessage("¿Deseas eliminar la cuenta "+email+"?")
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {
                            if(user.delete()){
                                Toast.makeText(Delete.this, "La cuenta "+user.getId()+" se ha eliminado correctamente", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Delete.this, AdminActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Delete.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Delete.this, AdminActivity.class);
                            startActivity(intent);
                        }
                    });
            builder.create().show();
        }else{
            Toast.makeText(Delete.this, "Permiso denegado", Toast.LENGTH_SHORT).show();
        }

    }
}