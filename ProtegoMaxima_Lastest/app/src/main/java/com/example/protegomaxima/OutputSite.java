package com.example.protegomaxima;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class OutputSite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_site);

        WebView webView = findViewById(R.id.webView);
        webView.loadUrl("https://sites.google.com/view/protegomaximafakebook");
    }
}