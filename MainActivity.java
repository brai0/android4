package com.example.fourthb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity
{
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;
    private final int CAMERA_REQ_CODE=100;
    ImageView imageSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageSelected = findViewById(R.id.imageView);

        findViewById(R.id.buttonSelectedImage).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,REQUEST_CODE_SELECT_IMAGE);
        });
        findViewById(R.id.buttonOpenCamera).setOnClickListener(view -> {
            Intent iCamera =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(iCamera,CAMERA_REQ_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            if(requestCode==REQUEST_CODE_SELECT_IMAGE){
            Uri selectedImageUri = data.getData();
            InputStream inputStream = null;
            try {
                inputStream = getContentResolver().openInputStream(selectedImageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            imageSelected.setImageBitmap(bitmap);
        }
        else if (requestCode==CAMERA_REQ_CODE)
            {
                assert data != null;
                Bitmap img= (Bitmap) data.getExtras().get("data");
                imageSelected.setImageBitmap(img);
            }
        }
    }
}
