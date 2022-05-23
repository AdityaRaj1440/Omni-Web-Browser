package com.example.webbrowser2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
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
    SwipeRefreshLayout swipe;
    int no= 1;
    Activity activity ;
    ProgressDialog progDialog;
    //final EditText urlET = findViewById(R.id.urlET);
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn= (Button) findViewById(R.id.tabs);
        btn.setText(""+no);
        sv= (SearchView) findViewById(R.id.searchBar);

        activity = this;


        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                url= "https://duckduckgo.com/?q="+s+"&ia=web";


//                Intent intent = new Intent(MainActivity.this, SearchResult.class);
//                intent.putExtra("url", url);
//                startActivity(intent);

                setContentView(R.layout.activity_search_result);
                final WebView wb = findViewById(R.id.searchResult);
                 EditText urlET = findViewById(R.id.urlET);
                final ImageView home = findViewById(R.id.home);


                urlET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER
                                || i == EditorInfo.IME_ACTION_DONE){
                            String curUrl= urlET.getText().toString();
                            if(!curUrl.contains(".com")){
                                curUrl= "https://duckduckgo.com/?q="+curUrl+"&ia=web";
                            }
                            InputMethodManager imm = (InputMethodManager)  getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(urlET.getWindowToken(), 0);
                            urlET.setCursorVisible(false);
                            wb.loadUrl(curUrl);
                            return true;
                        }
                        return false;
                    }
                });



                urlET.setText(url);

                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        wb.goBack();
                    }
                });


                WebAction(urlET);




                swipe = (SwipeRefreshLayout)findViewById(R.id.swipe);
                swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        urlET.setText(WebAction(urlET));
                        swipe.setRefreshing(false);
                    }
                });


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

    public String WebAction(EditText urlET) {

        progDialog = ProgressDialog.show(activity, "Loading","Please wait...", true);
        progDialog.setCancelable(false);

        wb= (WebView) findViewById(R.id.searchResult);
        WebSettings webSettings = wb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setDomStorageEnabled(true);

        wb.setWebViewClient(new WebViewClient(){


           @Override
            public void onPageFinished(WebView view, String url) {
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onPageStarted(WebView view, String urlExisting, Bitmap favicon) {
                super.onPageStarted(view, urlExisting, favicon);
                url = urlExisting;
                urlET.setText(url);


            }
        });

        wb.loadUrl(url);

        return url;

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

    public void setVisibility(android.view.View view)
    {
        EditText et= (EditText) findViewById(view.getId());
        et.setCursorVisible(true);
    }



}