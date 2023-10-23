package com.example.c56;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.c56.ContactItem; // Import your ContactItem class
import com.example.c56.ContactRecyclerView; // Import your ContactRecyclerView class

public class MainActivity extends AppCompatActivity {

    // Define a request code (you can choose any unique value)
    private static final int YOUR_REQUEST_CODE = 1;

    // Import url
    private String url;

    // Import ImageViews
    private ImageView textViewImage;
    private ImageView listViewImage;
    private ImageView recyclerViewImage;

    // Import clickable layouts
    private RelativeLayout textViewLayout;
    private RelativeLayout listViewLayout;
    private RelativeLayout recyclerViewLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Import ImageViews
        textViewImage = findViewById(R.id.textViewImage);
        listViewImage = findViewById(R.id.listViewImage);
        recyclerViewImage = findViewById(R.id.recyclerViewImage);

        // Import clickable layouts
        textViewLayout = findViewById(R.id.textViewLayout);
        listViewLayout = findViewById(R.id.listViewLayout);
        recyclerViewLayout = findViewById(R.id.recyclerViewLayout);

        // Example: Starting the ContactRecyclerView activity
        icon(textViewImage, textViewLayout, ContactRecyclerView.class);
        icon(listViewImage, listViewLayout, ContactListView.class); // You may have another activity for ListView
        icon(recyclerViewImage, recyclerViewLayout, ContactRecyclerView.class);

        checkPermission();
    }

    private void icon(ImageView imageView, RelativeLayout relativeLayout, final Class<?> activity) {
        url = getResources().getString(R.string.icon);
        // Load the icon
        Glide.with(this).load(url).into(imageView);

        // Click Listener for the layout
        relativeLayout.setOnClickListener(v -> startActivity(activity));
    }

    private void startActivity(final Class<?> activity) {
        if (checkPermission()) {
            Intent intent = new Intent(this, activity);
            startActivityForResult(intent, YOUR_REQUEST_CODE);
        }
    }

    // Check and request permission
    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // Permission denied
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            return false;
        }
        // Permission granted
        return true;
    }
}