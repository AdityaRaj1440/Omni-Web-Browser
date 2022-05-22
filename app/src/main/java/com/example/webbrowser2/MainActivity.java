package com.example.webbrowser2;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;
    private long pressedTime;
    public String url = "";
    WebView wb;
    SearchView sv;
    int no= 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn= (Button) findViewById(R.id.tabs);
        btn.setText(""+no);
        sv= (SearchView) findViewById(R.id.searchBar);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                url= "https://duckduckgo.com/?q="+s+"&ia=web";
//                Intent intent = new Intent(MainActivity.this, SearchResult.class);
//                intent.putExtra("url", url);
//                startActivity(intent);
                setContentView(R.layout.activity_search_result);
                wb= (WebView) findViewById(R.id.searchResult);
                WebSettings webSettings = wb.getSettings();
                webSettings.setJavaScriptEnabled(true);
                wb.setWebViewClient(new WebViewClient());
                wb.loadUrl(url);
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

    @Override
    public void onBackPressed() {
        try{
            if(!wb.canGoBack()){
                finish();
                startActivity(getIntent());
            }
            else{
                wb.goBack();
            }
        }catch (Exception e){
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
    }


    public void exit(android.view.View v){
        System.exit(1);
    }

    public void addTabs(android.view.View v){
            Button btn= (Button) findViewById(v.getId());
            no++;
            btn.setText(""+(no));
            Intent intent= new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);

    }


}