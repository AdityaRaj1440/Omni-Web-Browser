package com.example.webbrowser2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Bookmarks extends AppCompatActivity {
    myDbHandlerBook dbHandlerBook=new myDbHandlerBook(this,null,null,1);
        WebView mywebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

                final List<String> books=dbHandlerBook.databaseToString();
        if(books.size()>0)
        {
            ArrayAdapter myadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,books);
            ListView mylist=(ListView)findViewById(R.id.listViewBook);
            mylist.setAdapter(myadapter);

            mylist.setOnItemClickListener(
                    new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            /*String url=books.get(position);*/
                            String url=books.get(position);
                            setContentView(R.layout.activity_search_result);
                            WebView wb = findViewById(R.id.searchResult);
                            WebSettings webSettings = wb.getSettings();
                            webSettings.setJavaScriptEnabled(true);
                            webSettings.setSupportMultipleWindows(true);
                            webSettings.setDomStorageEnabled(true);

                            wb.setWebViewClient(new WebViewClient(){


                                @Override
                                public void onPageFinished(WebView view, String url) {

                                }

                                @Override
                                public void onPageStarted(WebView view, String urlExisting, Bitmap favicon) {
                                    super.onPageStarted(view, urlExisting, favicon);

                                    //Toast.makeText(getBaseContext(),"this works"+urlExisting,Toast.LENGTH_LONG).show();



                                }
                            });
                            TextView text = findViewById(R.id.urlET);
                            text.setText(url);
                            wb.loadUrl(url);

//                            Bundle urlBundle = new Bundle();
//                            urlBundle.putString("urls", url);
//                            Intent intent= new Intent(view.getContext(), MainActivity.class);
//                            intent.putExtras(urlBundle);
//
//                            startActivity(intent);



                        }
                    }
            );
        }

    }
}