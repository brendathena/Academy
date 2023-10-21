package com.example.c12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Fakebook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fakebook);

        WebView webView = findViewById(R.id.webView);
        webView.loadUrl("https://sites.google.com/view/protegomaximafakebook/home");
    }
}