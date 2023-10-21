package com.example.getcontacts;

import android.Manifest;
import androidx.appcompat.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView contacts;
    private boolean userAcceptedDialog = false;

    private boolean checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (userAcceptedDialog) {
                permissionDeniedDialog();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
                return false;
            }
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResult){
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
        if (requestCode == 1){
            if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED){
                if (userAcceptedDialog == false) {
                    contacts.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, Contacts.class);
                            startActivity(intent);
                        }
                    });
                }
            } else {
                dialogue();
            }
        }
    }


    private void dialogue(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You will not be able to share this app with your friends or check who is using our app. Are you sure you wish to continue?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    userAcceptedDialog = true;
                })
                .setNegativeButton("No", (dialog, which) -> {
                    checkPermission();
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void permissionDeniedDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You denied permission to access your contacts. To enable this feature, please go to the app settings and allow the 'Contacts' permission.")
                .setPositiveButton("Settings", (dialog, which) -> {
                    Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(android.net.Uri.parse("package:" + getPackageName()));
                    startActivity(intent);
                })
                .setNegativeButton("Okay",(dialog, which) -> {


                });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contacts = findViewById(R.id.contacts);
        Glide.with(this)
                .load("https://upload.wikimedia.org/wikipedia/commons/b/b7/Google_Contacts_logo.png")
                .into(contacts);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()){
                    Intent intent = new Intent(MainActivity.this, Contacts.class);
                    startActivity(intent);
                }
            }
        });
    }
}