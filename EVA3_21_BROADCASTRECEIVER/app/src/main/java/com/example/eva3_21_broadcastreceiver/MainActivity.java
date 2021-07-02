package com.example.eva3_21_broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtVwVisualizar;
    Intent inServicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtVwVisualizar = findViewById(R.id.txtVwVisualizar);
        inServicio = new Intent(this, MyService.class);
        BroadcastReceiver broadcastReceiver =  new MiBroadcast();
        IntentFilter intentFilter = new IntentFilter("MI_MENSAJE");
        registerReceiver(broadcastReceiver,intentFilter);
    }

    public void iniciar(View v){
        startService(inServicio);
    }
    public void detener(View v){
        stopService(inServicio);
    }

    class MiBroadcast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent){
            //LEE LOS MENSAJES DEL SERVICIO
            txtVwVisualizar.append(intent.getStringExtra("MENSAJE")+ "\n");
        }
    }
}