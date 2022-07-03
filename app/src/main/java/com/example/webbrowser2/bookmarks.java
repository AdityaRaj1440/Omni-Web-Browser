package com.example.webbrowser2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class bookmarks extends AppCompatActivity {

    myDbHandlerBook dbHandlerBook=new myDbHandlerBook(this,null,null,1);
    WebView mywebview;
   /* public static final String EXTRA_TEXT="com.jitendera.jitenderaarya.jitendera.EXTRA_TEXT";*/

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
                            Intent intent = new Intent(view.getContext(),MainActivity.class);

                           intent.putExtra("urls",url);

                            startActivity(intent);
                            finish();


                        }
                    }
            );
        }


    }
}
