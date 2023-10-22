package com.example.c56;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView ivTextView;
    ImageView ivListView;
    ImageView ivRecycleView;
    private String icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivTextView = findViewById(R.id.ivTextView);
        ivListView = findViewById(R.id.ivListView);
        ivRecycleView = findViewById(R.id.ivRecycleView);
        icon = getResources().getString(R.string.icon);


        //Load glide and setOnClickListener for each of the activities.
        icons(ivTextView, ContactTextView.class);
        icons(ivListView, ContactListView.class);
        icons(ivRecycleView, ContactRecyclerView.class);
    }

    private void icons (ImageView imageView, final Class<?> contactActivity){
        Glide.with(this).load(icon).into(imageView);

        imageView.setOnClickListener(v -> startActivity(contactActivity));
    }

    private void startActivity(final Class<?> contactActivity){
        if (checkPermission()){
            Intent intent = new Intent(this, contactActivity);
            startActivity(intent);
        }
    }

    //Check and request permissions.
    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, request it from the user
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            return false;
        }
        // Permission is granted
        return true;
    }

}