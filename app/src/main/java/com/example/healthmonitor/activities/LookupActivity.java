package com.example.healthmonitor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.example.healthmonitor.R;

public class LookupActivity extends AppCompatActivity implements View.OnClickListener {
    //views
    private EditText searchEt;
    private Button searchBtn;
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookup);

        // init views
        searchEt = findViewById(R.id.searchET);
        searchBtn = findViewById(R.id.searchBtn);
        webView = findViewById(R.id.webView);

        webView.loadUrl("https://www.google.com");
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        searchBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.equals(searchBtn)) {
            String url = searchEt.getText().toString();
            webView.loadUrl("https://www.google.com/search?q="+url);
        }
    }
}