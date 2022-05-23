package com.example.fourtha;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final int CAMERA_REQ_CODE=100;
    ImageView imgC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgC=findViewById(R.id.imgCamera);
        Button cameraBtn=findViewById(R.id.btnCamera);

        cameraBtn.setOnClickListener(view -> {
            Intent iCamera =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(iCamera,CAMERA_REQ_CODE);
        });


        }
    protected void onActivityResult(int  requestCode,int resultCode,@Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK){
            if(requestCode==CAMERA_REQ_CODE){
                assert data != null;
                Bitmap img= (Bitmap) data.getExtras().get("data");
                imgC.setImageBitmap(img);
            }
        }
    }}

