package com.example.healthmonitor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.example.healthmonitor.R;

public class LookupActivity extends AppCompatActivity implements View.OnClickListener {
    //views
    private EditText searchEt;
    private Button searchBtn;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookup);

        // init views
        searchEt = findViewById(R.id.searchET);
        searchBtn = findViewById(R.id.searchBtn);
        webView = findViewById(R.id.webView);

        searchBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.equals(searchBtn)) {
            String url = searchEt.getText().toString();
            webView.loadUrl("https://www.google.com/search?channel=fs&client=?&q="+url);
        }
    }
}