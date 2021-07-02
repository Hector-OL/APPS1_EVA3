package com.example.eva3_7_handler_banner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgVwBanner;
    Thread tBanner;
    int contador;
    //A través de un handler (método handlermessage) interactuar con la UI
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //Interactuar con la UI

            switch(contador) {
                case (0):
                    imgVwBanner.setImageResource(R.drawable.f1);
                    contador++;
                    break;
                case(1):
                    imgVwBanner.setImageResource(R.drawable.f2);
                    contador++;
                    break;
                case(2):
                    imgVwBanner.setImageResource(R.drawable.f3);
                    contador=0;
                    break;

            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgVwBanner = findViewById(R.id.imgVwBanner);

        tBanner = new Thread(){
            @Override
            public void run() {
                super.run();
                while(true){
                    try {
                        Thread.sleep(1000);
                        //Solciitar un mensaje
                        Message message = handler.obtainMessage();
                        //Enviar el mensaje
                        handler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        tBanner.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tBanner.interrupt();
    }
}