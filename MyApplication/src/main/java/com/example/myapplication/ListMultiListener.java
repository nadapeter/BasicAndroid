package com.example.myapplication;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by peterjlee on 7/14/13.
 */
public class ListMultiListener implements AbsListView.MultiChoiceModeListener {

    private ListActivity mListActivity;
    private HashSet<Integer> checkedItems;

    public ListMultiListener(ListActivity listActivity) {
        mListActivity = listActivity;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position,
                                          long id, boolean checked) {
        // Here you can do something when items are selected/de-selected,
        // such as update the title in the CAB
        if (checked) {
            checkedItems.add(position);
        } else {
            checkedItems.remove(position);
        }

    }

    private List getCheckedItems() {
        return Util.asSortedList(checkedItems);
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        // Respond to clicks on the actions in the CAB
        switch (item.getItemId()) {
            case R.id.option1:
                mListActivity.deleteSelectedItems(getCheckedItems());
                mode.finish(); // Action picked, so close the CAB
                return true;
            case R.id.option2:
                mListActivity.option2Selected();
                mode.finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        // Inflate the menu for the CAB
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        checkedItems = new HashSet<Integer>();
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        // Here you can make any necessary updates to the activity when
        // the CAB is removed. By default, selected items are deselected/unchecked.
        checkedItems = null;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        // Here you can perform updates to the CAB due to
        // an invalidate() request
        return false;
    }
}
