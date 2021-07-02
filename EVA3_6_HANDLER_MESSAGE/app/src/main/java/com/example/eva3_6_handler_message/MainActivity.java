package com.example.eva3_6_handler_message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtVwMen;
    Thread thread;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //AQUI PODEMOS MODIFICAR LA INTERFAZ GRÁFICA
            //TRABAJO LIGERO --> Tarea intensa va a trabar la ui
            String cade = (String) msg.obj;
            int what = msg.what;
            txtVwMen.append("El hilo = " + what + " imprime "+ cade + "\n");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtVwMen = findViewById(R.id.txtViewMen);
        //txtVwMen.setText("Hola mundo");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(true){
                    try {
                        Thread.sleep(1000);
                        String cade = "i = " +i;
                        i++;
                        //solciitamos un mensaje
                        //ponemos informacion en el mensaje y finalmente lo devolvemos
                        Message message = handler.obtainMessage(1000,cade);
                        //se devuelve
                        handler.sendMessage(message);
                        Log.wtf("runnable",cade);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        thread = new Thread(runnable);
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread.interrupt();
    }
}