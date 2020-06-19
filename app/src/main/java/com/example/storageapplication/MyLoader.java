package com.example.storageapplication;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.NonNull;
import androidx.loader.content.CursorLoader;

class MyLoader extends CursorLoader {
    private Context context;
    public MyLoader(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public Cursor loadInBackground() {

        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        return databaseHelper.getWordList();
    }
}
