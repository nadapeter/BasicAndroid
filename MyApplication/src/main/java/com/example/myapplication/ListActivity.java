package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peterjlee on 7/14/13.
 */
public class ListActivity extends SherlockActivity implements AdapterView.OnItemLongClickListener {

    private Context context;
    private ListView listView;
    private ListAdapterSample adapter;
    private ActionMode mMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        listView = (ListView) findViewById(R.id.listview);
        context = this.getApplicationContext();
        adapter = new ListAdapterSample(context, 25);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //listView.setItemsCanFocus(false);
        listView.setOnItemLongClickListener(this);
        //listView.setMultiChoiceModeListener(new ListMultiListener(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mMode == null) {
                    finish();
                }
            }
        });
    }

    public void deleteSelectedItems(List list) {
        Toast.makeText(context, "Delete Selected Items", Toast.LENGTH_LONG).show();
        adapter.deleteCheckedItems(list);
    }

    public void option2Selected() {
        Toast.makeText(context, "option 2 selected", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        // Notice how the ListView api is lame
        // You can use mListView.getCheckedItemIds() if the adapter
        // has stable ids, e.g you're using a CursorAdaptor
        if (mMode == null) {
            mMode = startActionMode(new ModeCallback());
            listView.setItemChecked(position, true);
        }

        return true;
    };

    private final class ModeCallback implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Create the menu from the xml file
            MenuInflater inflater = getSupportMenuInflater();
            inflater.inflate(R.menu.menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            // Here, you can checked selected items to adapt available actions
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            // Destroying action mode, let's unselect all items
            for (int i = 0; i < listView.getAdapter().getCount(); i++)
                listView.setItemChecked(i, false);

            if (mode == mMode) {
                mMode = null;
            }
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.option1:
                    deleteSelected();
                    mode.finish();
                    return true;
                case R.id.option2:
                    finish();
                    return true;
                default:
                    return false;
            }
        }
    };

    public void deleteSelected() {
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        int numChecked = 0;
        ArrayList<Integer> checkedPos = new ArrayList<Integer>();
        String checkedNums = "";
        for (int i = 0; i < checked.size(); i++) {
            if(checked.valueAt(i)) {
                numChecked++;
                checkedPos.add(checked.keyAt(i));
                checkedNums = checkedNums + ", " + checked.keyAt(i);
            }
        }
        java.util.Collections.sort(checkedPos);

        adapter.deleteCheckedItems(checkedPos);
        Toast.makeText(this, "#checked: " + numChecked + " at: " + checkedNums, Toast.LENGTH_LONG).show();
    }
}
