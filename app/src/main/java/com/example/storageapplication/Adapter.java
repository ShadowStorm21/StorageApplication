package com.example.storageapplication;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

class Adapter extends CursorAdapter {


    public Adapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(cursor.getString(cursor.getColumnIndex("WORD")));
    }
}
