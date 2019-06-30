package com.example.adita.app_pingui_g8;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RequestQueue mQueue=null;
    private String token=null;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue= Volley.newRequestQueue(this);
    }

    /**
     * Método que captura los datos ingresados por la persona en la interfaz
     * gráfica y llama a la función iniciarSesión para validar dichos datos
     * @param v para reconocer que el método es para ser usado por los botones
     *          método 'OnClick'
     */
    public void login(View v){
        final EditText dt1=(EditText) findViewById(R.id.txtuser);
        final EditText dt2=(EditText) findViewById(R.id.txtpassword);
        String usuario=dt1.getText().toString();
        String contrasena=dt2.getText().toString();
        iniciarSesion(usuario,contrasena);

    }

    /**
     * Método que se encarga de validar el usuario y las contraseñas
     * ingresadas para logonearse al menú de la APP y enviar el token a la
     * siguiente actividad
     * @param usuario es una cadena de texto con la que se identifica la persona
     * @param contrasena es una cadena de texto que solo conoce la persona
     */

    private void iniciarSesion(String usuario, String contrasena) {
        Map<String, String> params=new HashMap<>();
        params.put("username", usuario);
        params.put("password", contrasena);

        JSONObject parametros=new JSONObject(params);
        String URL = "https://amstdb.herokuapp.com/db/nuevo-jwt";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, parametros, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                try {
                    token = response.getString("token");
                    Intent i = new Intent(getBaseContext(), parametrosControl.class);
                    i.putExtra("token", token);
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Alerta");
                alertDialog.setMessage("Credenciales Incorrectas");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int
                which) {
                    dialog.dismiss();
                }
            });
                alertDialog.show();
            }
        });
        mQueue.add(request);
    }
}