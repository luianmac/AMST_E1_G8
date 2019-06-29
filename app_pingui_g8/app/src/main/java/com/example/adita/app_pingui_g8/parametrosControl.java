package com.example.adita.app_pingui_g8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class parametrosControl extends AppCompatActivity {
    String token = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametros_control);
        Intent login = getIntent();
        this.token = (String)login.getExtras().get("token");
    }

    public void Salir(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        stopService(intent);
        finish();
        //System.exit(0);
    }

    public void mostrarTemperatura(View v){
        Intent temperatura = new Intent(getBaseContext(),temperatura.class);
        temperatura.putExtra("token", token);
        startActivity(temperatura);
    }

    public void mostrarRecorrido(View v){
        Intent recorrido = new Intent(getBaseContext(),recorrido.class);
        recorrido.putExtra("token", token);
        startActivity(recorrido);
    }
}
