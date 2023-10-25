package com.example.doodle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.doodle.fragment.AnimateViewFragment;
import com.example.doodle.fragment.DoodleViewFragment;
import com.example.doodle.fragment.PaintViewFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{
    NavigationBarView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView
                = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.paintview);
    }

    PaintViewFragment paintViewFragment = new PaintViewFragment();
    AnimateViewFragment animateViewFragment = new AnimateViewFragment();
    DoodleViewFragment doodleViewFragment = new DoodleViewFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.paintview) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, paintViewFragment)
                    .commit();
            return true;
        } else if (itemId == R.id.animateview) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, animateViewFragment)
                    .commit();
            return true;
        } else if (itemId == R.id.doodle) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, doodleViewFragment)
                    .commit();
            return true;
        }
        return false;
    }
}