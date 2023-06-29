package com.example.login;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class TopBarManagerTool {
    private MaterialToolbar topAppBar;

    public void setupTopBar(AppCompatActivity activity) {
        topAppBar = activity.findViewById(R.id.topAppBarTool);
        activity.setSupportActionBar(topAppBar);
    }

    public void setNavigationIcon(Drawable icon, View.OnClickListener listener) {
        topAppBar.setNavigationIcon(icon);
        topAppBar.setNavigationOnClickListener(listener);
    }
}
