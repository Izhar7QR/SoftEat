package com.example.login;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class TopBarManager {
    private MaterialToolbar topAppBar;
    private AppCompatActivity activity;

    public void setupTopBar(AppCompatActivity activity, MaterialToolbar topAppBar) {
        this.activity = activity;
        this.topAppBar = topAppBar;
        activity.setSupportActionBar(topAppBar);
    }

    public void setNavigationIcon(Drawable icon, View.OnClickListener listener) {
        topAppBar.setNavigationIcon(icon);
        topAppBar.setNavigationOnClickListener(listener);
    }
    public void setButton1ClickListener() {
        topAppBar.setOnMenuItemClickListener(new MaterialToolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Verifica el ID del botón presionado
                if (item.getItemId() == R.id.account_settings_item) {

                    Toast.makeText(topAppBar.getContext(), "Hola Mi cuenta", Toast.LENGTH_SHORT).show();
                    // Navega a la Activity1
                    Intent intent = new Intent(activity, ConversorUnidades.class);
                    activity.startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

}
