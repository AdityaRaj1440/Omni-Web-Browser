package com.example.webbrowser2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class SearchResult extends AppCompatActivity {


    WebView wb;
    MyDbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        dbHandler= new MyDbHandler(this,null,null,1);

        wb= (WebView) findViewById(R.id.searchResult);
        WebSettings webSettings = wb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wb.setWebViewClient(new WebViewClient());
        String url = getIntent().getStringExtra("url");
        wb.loadUrl(url);



    }

    private void saveData()
    {
        Websites webv=new Websites(wb.getUrl());
        dbHandler.addUrl(webv);
    }


}