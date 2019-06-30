package com.example.adita.app_pingui_g8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class parametrosControl extends AppCompatActivity {
    String token = "";

    /**
     * Método que permite la ejecución de la Activity y captura el token de
     * la anterior actividad
     * @param savedInstanceState guarda el estado de la instancia
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametros_control);
        Intent login = getIntent();
        this.token = (String)login.getExtras().get("token");
    }

    /**
     * Método para finalizar la ejecución de la app
     * @param v para reconocer que el método es para ser usado por el boton 'SALIR'
     *        método 'OnClick'
     */
    public void Salir(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        stopService(intent);
        finish();
        //System.exit(0);
    }

    /**
     * Método para mostrar el registro de temperatura almacenado en la base de datos DJANGO
     * y envía el token a la siguiente actividad
     * @param v para reconocer que el método es para ser usado por el boton 'TEMPERATURA'
     *          método 'OnClick'
     */
    public void mostrarTemperatura(View v){
        Intent temperatura = new Intent(getBaseContext(),temperatura.class);
        temperatura.putExtra("token", token);
        startActivity(temperatura);
    }

    /**
     * Método para mostrar el registro de los recorridos realizados almacenados en la base
     * de datos DJANGO y envía el token a la siguiente actividad.
     * @param v para reconocer que el método es para ser usado por el boton 'RECORRIDO'
     *          método 'OnClick'
     */
    public void mostrarRecorrido(View v){
        Intent recorrido = new Intent(getBaseContext(),recorrido.class);
        recorrido.putExtra("token", token);
        startActivity(recorrido);
    }
}
