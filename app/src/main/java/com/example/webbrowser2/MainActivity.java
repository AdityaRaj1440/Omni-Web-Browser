package com.example.webbrowser2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    SearchView sv;
    public String url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sv= (SearchView) findViewById(R.id.searchBar);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
//                WebView tv= (WebView) findViewById(R.id.search);
//                WebSettings webSettings = tv.getSettings();
//                webSettings.setJavaScriptEnabled(true);
//                tv.loadUrl("https://duckduckgo.com/?q="+s+"&ia=web");
                url= "https://duckduckgo.com/?q="+s+"&ia=web";
                Intent intent = new Intent(MainActivity.this, SearchResult.class);
                intent.putExtra("url", url);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
//        ImageView im= (ImageView) findViewById(R.id.imageView);
//        Picasso.get().load("https://wallpaperaccess.com/thumb/36626.jpg").into(im);

    }

    public void exit(android.view.View v){
        System.exit(1);
    }
}