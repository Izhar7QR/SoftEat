package com.example.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class RecuperarPsw extends AppCompatActivity {

    Button btnUpdatePsw;
    EditText CorreoR, nombre;
    String CorreoRecu, name;
    StringRequest stringRequest;
    String URL = "http://192.168.1.67/android_distribuidora/forgotpassword.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_psw);

        //btnSubmit = findViewById(R.id.submit);
        CorreoR = findViewById(R.id.correoR);
        nombre = findViewById(R.id.name);

        btnUpdatePsw = findViewById(R.id.btnUpdatePsw);

        //submith();

        btnUpdatePsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecuperarPsw.this, UpdatePsw.class);
                startActivity(intent);
            }
        });

    }



    public void btnRecuperar(View view){
        String nombres = nombre.getText().toString().trim();
        String email = CorreoR.getText().toString().trim();

        if (nombres.isEmpty() || email.isEmpty()) {
            Snackbar.make(view, "Por favor completa todos los campos", Snackbar.LENGTH_LONG).show();
            return;
        }
/*
        if (!password.equals(confirmarPassword)) {
            Snackbar.make(view, "Las contraseñas no coinciden", Snackbar.LENGTH_LONG).show();
            return;
        }
*/
        String url = "http://192.168.17.96/android_distribuidora/forgotpassword.php";

        final Map<String, String> parametros = new HashMap<>();
        parametros.put("name", nombre.getText().toString());
        parametros.put("email", CorreoR.getText().toString());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Snackbar.make(view, "Correo enviado correctamente", Snackbar.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar.make(view, "Error " + error.toString(), Snackbar.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                return parametros;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }
}