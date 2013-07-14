package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by peterjlee on 7/14/13.
 */
public class ListAdapterSample extends ArrayAdapter<String> {

    private Context context;
    private final LayoutInflater inflater;

    public ListAdapterSample(Context context, int size) {
        super(context, -1);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        for (int i = 0; i < size; i++) {
            add("Item" + String.valueOf(i));
        }
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = view==null ? createView(parent) : view;
        bindView(position, view);
        return view;
    }

    private void bindView(int position, View view) {
        String item = getItem(position);
        TextView tv = (TextView) view.findViewById(R.id.textview);
        tv.setText(item);
    }

    private View createView(ViewGroup parent) {
        View view = inflater.inflate(R.layout.list_row, parent, false);
        return view;
    }
}
