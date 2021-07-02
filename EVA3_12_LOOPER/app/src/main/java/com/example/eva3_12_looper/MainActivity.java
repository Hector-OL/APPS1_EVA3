package com.example.eva3_12_looper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtVw1;

    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            txtVw1.append((String)msg.obj + "\n");
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            for(int i = 0;i<20;i++){
                try {
                    Thread.sleep(1000);
                    Message message = handler.obtainMessage(100,"i = "+ i);
                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtVw1 = findViewById(R.id.txtVw1);
        Thread thread = new Thread(runnable);
        thread.start();
    }
}