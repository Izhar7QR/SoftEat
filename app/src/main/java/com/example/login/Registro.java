package com.example.login;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.widget.AppCompatButton;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtEmail;
    private EditText txtUsuario;
    private EditText txtPass;
    private EditText txtConfirmPass;
    private boolean passwordShowing = false;
    private boolean conPasswordShowin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txtNombre = findViewById(R.id.nameET);
        txtUsuario= findViewById(R.id.usuarioET);
        txtEmail = findViewById(R.id.emailET);
        txtPass = findViewById(R.id.passwordET);
        txtConfirmPass = findViewById(R.id.conPasswordET);
    }

    public void clickBtnInsertar(View view) {
        String nombre = txtNombre.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String usuario = txtUsuario.getText().toString().trim();
        String password = txtPass.getText().toString().trim();
        String confirmarPassword = txtConfirmPass.getText().toString().trim();

        if (nombre.isEmpty() || email.isEmpty() || usuario.isEmpty() || password.isEmpty() || confirmarPassword.isEmpty()) {
            Snackbar.make(view, "Por favor completa todos los campos", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (!password.equals(confirmarPassword)) {
            Snackbar.make(view, "Las contraseñas no coinciden", Snackbar.LENGTH_LONG).show();
            return;
        }

        String url = "http://192.168.1.70/softeat/insertar.php";

        final Map<String, String> parametros = new HashMap<>();
        parametros.put("nombre", txtNombre.getText().toString());
        parametros.put("usuario", txtUsuario.getText().toString());
        parametros.put("correo", txtEmail.getText().toString());
        parametros.put("psw", txtPass.getText().toString());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Snackbar.make(view, "Felicidades usuario agregado exitosamente", Snackbar.LENGTH_LONG).show();
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


