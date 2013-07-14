package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by peterjlee on 7/14/13.
 */
public class ListActivity extends Activity {

    private Context context;
    private ListView listView;
    private ListAdapterSample adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        listView = (ListView) findViewById(R.id.listview);
        context = this;
        adapter = new ListAdapterSample(context, 25);
        listView.setAdapter(adapter);
    }
}
