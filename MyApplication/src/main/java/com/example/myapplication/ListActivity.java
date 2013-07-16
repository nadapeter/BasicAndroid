package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

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
        context = this.getApplicationContext();
        adapter = new ListAdapterSample(context, 25);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new ListMultiListener(this));
    }

    public void deleteSelectedItems(List list) {
        Toast.makeText(context, "Delete Selected Items", Toast.LENGTH_LONG).show();
        adapter.deleteCheckedItems(list);
    }

    public void option2Selected() {
        Toast.makeText(context, "option 2 selected", Toast.LENGTH_LONG).show();

    }
}
