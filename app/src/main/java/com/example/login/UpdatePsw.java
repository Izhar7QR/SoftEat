package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UpdatePsw extends AppCompatActivity {

    private EditText txtTokens, txtPsw, txtCPsw;
    private Button btnCambiarPsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_psw);
        txtTokens = findViewById(R.id.txtToken);
        txtPsw = findViewById(R.id.txtPsw);
        txtCPsw = findViewById(R.id.txtConfirmarPswS);

        btnCambiarPsw = findViewById(R.id.btnCambiarPsw);




    }

    public void btnUpdate (View view){
        String token = txtTokens.getText().toString().trim();
        String password = txtPsw.getText().toString().trim();
        String confirmarPassword = txtCPsw.getText().toString().trim();

        if (token.isEmpty() || password.isEmpty() || confirmarPassword.isEmpty()) {
            Snackbar.make(view, "Por favor completa todos los campos", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (!password.equals(confirmarPassword)) {
            Snackbar.make(view, "Las contraseñas no coinciden", Snackbar.LENGTH_LONG).show();
            return;
        }

        String url = "http://192.168.17.96/android_distribuidora/update.php";

        final Map<String, String> parametros = new HashMap<>();
        parametros.put("token", txtTokens.getText().toString());
        parametros.put("password", txtCPsw.getText().toString());
        String newtoken = generateToken(8);
        parametros.put("newToken", newtoken);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Snackbar.make(view, "Credenciales actualizadas", Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent(UpdatePsw.this, Login.class);
                        startActivity(intent);
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
    public static String generateToken(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }

        return password.toString();
    }
}