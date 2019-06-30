package com.example.adita.app_pingui_g8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class recorrido extends AppCompatActivity {

    private RequestQueue mQueue;
    private String token = "";

    /**
     * Método que permite la ejecución de la Activity y captura el token de
     * la anterior actividad
     * @param savedInstanceState guarda el estado de la instancia
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorrido);
        mQueue = Volley.newRequestQueue(this);
        Intent login = getIntent();
        this.token = (String)login.getExtras().get("token");

        final TextView tv = (TextView) findViewById(R.id.txtRecorrido);
        final TextView tv2 = (TextView) findViewById(R.id.txtRecorrido2);
        final TextView tv3 = (TextView) findViewById(R.id.txtRecorrido3);
        final TextView tv4 = (TextView) findViewById(R.id.txtRecorrido4);
        ArrayList<TextView> a = new ArrayList<>();
        a.add(tv);
        a.add(tv2);
        a.add(tv3);
        a.add(tv4);
        for(int i=0; i<4; i++) {
            String url = "https://amstdb.herokuapp.com/db/recorrido/" + i;
            revisarRecorrido(url,a.get(i));
        }
    }

    /**
     * Método que establece conexión con la base de datos y obtiene los datos detallados
     * en formato JSON
     * @param url link de la base da datos a establecer conexióm
     * @param a cuadro de texto de la interfaz en donde se van a escribir los datos obtenidos
     */
    private void revisarRecorrido(String url, final TextView a) {
        //final TextView tv = (TextView) findViewById(R.id.txtRecorrido);
        //String url_Recorrido = "https://amstdb.herokuapp.com/db/recorrido";
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            a.setText("Origen: "+response.getString("origen")+"\n"+"Destino:    "+response.getString("destino")+"\n"+"Fecha de Salida: "+response.getString("fecha_inicio"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "JWT " + token);
                System.out.println(token);
                return params;
            }
        };;
        mQueue.add(request);
    }
}
