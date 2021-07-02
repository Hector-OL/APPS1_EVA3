package com.example.eva3_9_handler_post;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtVwShow;
    Handler handler = new Handler();
    Runnable background = new Runnable() {
        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(1000);
                    handler.post(foreground);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    };
    //TRABAJO CON UI
    Runnable foreground = new Runnable() {
        @Override
        public void run() {
            txtVwShow.append("Hola mundo!! \n" );
        }
    };
    Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtVwShow = findViewById(R.id.txtVwShow);
        thread = new Thread(background);
        thread.start();
    }
}