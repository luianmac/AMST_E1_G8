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

import java.util.HashMap;
import java.util.Map;

public class temperatura extends AppCompatActivity {
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
        setContentView(R.layout.activity_temperatura);
        mQueue = Volley.newRequestQueue(this);
        Intent login = getIntent();
        this.token = (String)login.getExtras().get("token");
        revisarTemperatura();
    }

    /**
     * Método que captura el valor temperatura almacenado en la base de datos
     * y los muetra en el cuadro de texto de la interfaz gráfica
     */
    private void revisarTemperatura() {
        final TextView tv = (TextView) findViewById(R.id.txtTemperatura);
        String url_Temp = "https://amstdb.herokuapp.com/db/registroDeFrios/20";
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url_Temp, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            tv.setText(response.getString("temperatura")+ " C");
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