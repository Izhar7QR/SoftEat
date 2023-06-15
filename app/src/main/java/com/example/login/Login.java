package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import android.content.SharedPreferences;

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
    private TextView btnRegistro, btnRecuperar;
    private RadioButton RBsesion;
    private boolean isActivateRadioButton;
    private static final String STRING_PREFERENCES = "com.example.login";
    private static final String PREFERENCE_ESTADO_BUTTON_SESION = "estado.button.sesion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.emailEditText);
        txtPass = findViewById(R.id.passwordEditText);
        btnRegistro = findViewById(R.id.signBtn);
        btnRecuperar = findViewById(R.id.recuperarC);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Registro.class);
                startActivity(intent);
            }
        });

        if (obtenerEstadoButton()){
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }

        RBsesion = (RadioButton) findViewById(R.id.RBSesion);
        isActivateRadioButton = RBsesion.isChecked(); //DESACTIVADO
        RBsesion.setOnClickListener(new View.OnClickListener() {
            //ACTIVADO
            @Override
            public void onClick(View v) {
                if (isActivateRadioButton){
                    RBsesion.setChecked(false);
                }
                isActivateRadioButton = RBsesion.isChecked();
            }
        });
    }
    public void guardarEstadoButton(){
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        preferences.edit().putBoolean(PREFERENCE_ESTADO_BUTTON_SESION, RBsesion.isChecked()).apply();
    }


    public boolean obtenerEstadoButton(){
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        return preferences.getBoolean(PREFERENCE_ESTADO_BUTTON_SESION, false);
    }

    public void clickBtnLogin(View view) {
        String email = txtEmail.getText().toString().trim();
        String password = txtPass.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Snackbar.make(view, "Por favor completa todos los campos", Snackbar.LENGTH_LONG).show();
            return;
        }

        String url = "http://192.168.17.96/android_distribuidora/consult.php";

        final Map<String, String> parametros = new HashMap<>();
        parametros.put("correo", email);
        parametros.put("contrasena", password);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        guardarEstadoButton();
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

    public void clickBtnRecuperar(View view) {
        Intent intent = new Intent(Login.this, RecuperarPsw.class);
        startActivity(intent);

/*
        String email = txtEmail.getText().toString().trim();

        if (email.isEmpty()) {
            Snackbar.make(view, "Por favor ingresa tu correo electrónico", Snackbar.LENGTH_LONG).show();
            return;
        }

        String url = "http://192.168.17.96/softeat/recuperar_contrasena.php";

        final Map<String, String> parametros = new HashMap<>();
        parametros.put("correo", email);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Snackbar.make(view, response, Snackbar.LENGTH_LONG).show();
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
*/
    }
}
