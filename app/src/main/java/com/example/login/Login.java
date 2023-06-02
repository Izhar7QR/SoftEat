package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.google.android.gms.common.api.Response;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response;


public class Login extends AppCompatActivity {
    private EditText txtEmail;
    private EditText txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.emailEditText);
        txtPass = findViewById(R.id.passwordEditText);
    }

    public void clickBtnLogin(View view) {
        String email = txtEmail.getText().toString().trim();
        String password = txtPass.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Snackbar.make(view, "Por favor completa todos los campos", Snackbar.LENGTH_LONG).show();
            return;
        }

        String url = "http://192.168.1.70/softeat/consult.php";

        final Map<String, String> parametros = new HashMap<>();
        parametros.put("correo", email);
        parametros.put("contrasena", password);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Verificar la respuesta del servidor
                        if (response.contains("Error")) {
                            Snackbar.make(view, response, Snackbar.LENGTH_LONG).show();
                        } else {
                            // Inicio de sesión exitoso, redirigir a MainActivity
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
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
