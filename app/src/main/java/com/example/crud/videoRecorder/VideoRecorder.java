package com.example.crud.videoRecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.crud.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VideoRecorder extends AppCompatActivity {

    Spinner spiList;
    ImageButton btnGrabar;
    Button btnVer;
    VideoView vidGuarda;
    String[] lista;
    // private static final int TOMAR_VIDEO = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_recorder);

        spiList = findViewById(R.id.spiList);
        btnGrabar = findViewById(R.id.imgbtnGrabar);
        btnVer = findViewById(R.id.btnVer);
        vidGuarda = findViewById(R.id.vidGuarda);
        lista = fileList();
        ArrayAdapter<String> adpt01 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lista);
        spiList.setAdapter(adpt01);

        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(intent, 3);

            }
        });

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int post = spiList.getSelectedItemPosition();
                vidGuarda.setVideoPath(getFilesDir()+"/"+lista[post]);
                vidGuarda.start();
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 3 && resultCode == RESULT_OK){
            Uri videoUri = data.getData();
            vidGuarda.setVideoURI(videoUri);
            vidGuarda.start();
            try {
                AssetFileDescriptor videoAsset = getContentResolver().openAssetFileDescriptor(data.getData(), "r");
                FileInputStream in = videoAsset.createInputStream();
                FileOutputStream archivo = openFileOutput(crearNombreArchivomp4(), Context.MODE_PRIVATE);
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0){
                    archivo.write(buf, 0, len);
                }
            }
            catch (FileNotFoundException e) {
                Toast.makeText(this, "Problemas", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(this, "Problemas", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String crearNombreArchivomp4(){
        String fecha = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombre = fecha + ".mp4";
        return nombre;
    }
}