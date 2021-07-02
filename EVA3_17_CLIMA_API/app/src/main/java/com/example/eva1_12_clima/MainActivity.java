package com.example.eva1_12_clima;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.AsynchronousChannelGroup;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*Clima[] aClimaCd = {
            new Clima(R.drawable.sunny,"Chihuahua",15,"Despejado con viento"),
            new Clima(R.drawable.atmospher,"Delicias",28,"Mucho viento"),
            new Clima(R.drawable.cloudy,"Cuauhtemoc",15,"Nublado"),
            new Clima(R.drawable.light_rain,"Juarez",10,"Brisa primaveral"),
            new Clima(R.drawable.rainy,"Camargo",28,"Lluvioso"),
            new Clima(R.drawable.snow,"Parral",30,"Chubascos de nieve"),
            new Clima(R.drawable.thunderstorm,"Buenaventura",23,"Tormenta eléctrica"),
            new Clima(R.drawable.tornado,"Villa Ahumada",12,"Tornadoo"),
            new Clima(R.drawable.sunny,"Chihuahua",15,"Soleadísimo"),
            new Clima(R.drawable.light_rain,"Juarez",10,"Hay algo de lluvia"),

    };*/
    List<Clima> lstCiudades = new ArrayList<>();

    ListView lstVwClima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lstVwClima = findViewById(R.id.lstVwClima);
       // lstVwClima.setAdapter(new ClimaAdaptador(this,R.layout.mi_lista_clima,aClimaCd));
        ConexionClima cc = new ConexionClima();
        cc.execute("http://api.openweathermap.org/data/2.5/find?lat=28.6&lon=-106&cnt=30&units=metric&appid=78cf9f34956433dba8fee4f18ba0f466");
    }
                                    //URL, NADA, JSON (STRING)
    class ConexionClima extends AsyncTask<String, Void, String> {//AQUI se hace la conexión (trabajo en segundo plano)
        @Override
        protected String doInBackground(String... strings) {
            String sUrl = strings[0];
            String sResu = null;

            //httpURLConnection
            try {
                URL url = new URL(sUrl);
                //aqui se realiza la conexión
                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                //aqui se verifica la conexión
                if(httpCon.getResponseCode()==HttpURLConnection.HTTP_OK){
                    //aqui es como leer un archivo de texto
                    InputStreamReader isReader = new InputStreamReader(httpCon.getInputStream());
                    BufferedReader  brDatos = new BufferedReader(isReader);
                    sResu = brDatos.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sResu;
        }

        //aqui vamos a llenar la lista con datos
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(!s.equals("") || s == null){//verificar que tengamos un respuesta
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    //recuperar el arreglo de ciudades
                    JSONArray jsaCiudades = jsonObject.getJSONArray("list");
                    for(int i = 0; i<jsaCiudades.length(); i++){
                        JSONObject jsonCiudad = jsaCiudades.getJSONObject(i);//recuperar una ciudad particular
                        //leer ciudad
                        Clima climaCiudad = new Clima();
                        climaCiudad.setCiudad(jsonCiudad.getString("name"));
                        JSONObject jsonMain = jsonCiudad.getJSONObject("main");
                        climaCiudad.setTemp(jsonMain.getDouble("temp"));
                        JSONArray jsaWeather = jsonCiudad.getJSONArray("weather");
                        //tomamos el primer elemento
                        JSONObject jsonClimaActual = jsaWeather.getJSONObject(0);
                        climaCiudad.setDesc(jsonClimaActual.getString("description"));
                        int id = jsonClimaActual.getInt("id");
                        if(id < 300){ //tormentas
                            climaCiudad.setImagen(R.drawable.thunderstorm);
                        }else if(id <400){ //lluvia ligera
                            climaCiudad.setImagen(R.drawable.light_rain);
                        }else if(id < 600){
                            climaCiudad.setImagen(R.drawable.rainy);
                        }else if(id < 700){
                        climaCiudad.setImagen(R.drawable.snow);
                        }else if(id < 801){
                        climaCiudad.setImagen(R.drawable.sunny);
                        }else if(id < 900){
                            climaCiudad.setImagen(R.drawable.cloudy);
                        }else {
                            climaCiudad.setImagen(R.drawable.tornado);
                        }
                        lstCiudades.add(climaCiudad);


                    }
                    lstVwClima.setAdapter(new ClimaAdaptador(MainActivity.this,R.layout.mi_lista_clima,lstCiudades));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}