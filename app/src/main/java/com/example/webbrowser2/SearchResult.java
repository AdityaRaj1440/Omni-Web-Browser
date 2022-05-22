package com.example.webbrowser2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SearchResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        WebView wb= (WebView) findViewById(R.id.searchResult);
        WebSettings webSettings = wb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wb.setWebViewClient(new WebViewClient());
        String url = getIntent().getStringExtra("url");
        wb.loadUrl(url);
    }
}