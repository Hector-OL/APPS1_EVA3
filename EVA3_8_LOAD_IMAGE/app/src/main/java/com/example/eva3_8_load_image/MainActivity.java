package com.example.eva3_8_load_image;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView imgVwLoad;
    Bitmap bitmap;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            imgVwLoad.setImageBitmap(bitmap);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgVwLoad = findViewById(R.id.imVwLoad);
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                bitmap = descargarImagen("https://i.pinimg.com/originals/8d/a8/ae/8da8aea3377f74b824935440acf0545c.jpg");
                Message msg = handler .obtainMessage();
                handler.sendMessage(msg);
            }
        };
        thread.start();
    }


    private Bitmap descargarImagen(String url){
        try {
            InputStream inputStream = (InputStream) new URL(url).getContent();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

