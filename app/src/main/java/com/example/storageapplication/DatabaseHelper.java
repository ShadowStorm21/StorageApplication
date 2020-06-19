package com.example.storageapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Dic.db";
    private static final String TABLE_NAME = "DICTIONARY";
    private static final String WORD_FIELD = "WORD";
    private static final String DEFINITION_FIELD = "DEFINITION";
    private static final int VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+ TABLE_NAME + "(_id INTEGER PRIMARY KEY, "+ WORD_FIELD + " TEXT, "+ DEFINITION_FIELD + " TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }


    public long findId(String word)
    {
        long val = -1;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT _id FROM " + TABLE_NAME + " WHERE " + WORD_FIELD + " = ? ",new String[]{word});

        if(cursor.getCount() == 1)
        {
            cursor.moveToFirst();
            val = cursor.getInt(0);
        }
        return val;
    }

    public String getWord(long id)
    {
        String val = "";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT "+ WORD_FIELD + " FROM " + TABLE_NAME +  " WHERE " + " _id = ? ", new String[]{String.valueOf(id)});

        if(cursor.getCount() == 1)
        {
            cursor.moveToFirst();
            val = cursor.getString(0);
        }

        return val;
    }

    public Cursor getWordList()
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery("SELECT _id, " + WORD_FIELD + " FROM " + TABLE_NAME + " ORDER BY " + WORD_FIELD + " ASC ", null );
    }

    public void saveRecord(String word, String definition)
    {
        long id = findId(word);

        if(id > 0)
        {
            updateRecord(id,word,definition);
        }
        else
        {
            addRecord(word,definition);
        }
    }

    public long addRecord(String word, String definition)
    {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WORD_FIELD,word);
        contentValues.put(DEFINITION_FIELD,definition);
        return database.insert(TABLE_NAME,null,contentValues);
    }
    public int updateRecord(long id , String word, String definition)
    {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id",id);
        contentValues.put(WORD_FIELD,word);
        contentValues.put(DEFINITION_FIELD,definition);
        return database.update(TABLE_NAME,contentValues,"_id = ? ",new String[]{String.valueOf(id)});
    }
    public int deleteRecord(long id)
    {
        SQLiteDatabase database = getWritableDatabase();
        return database.delete(TABLE_NAME," _id = ? ", new String[]{String.valueOf(id)});
    }
    public String findDefinition(long id)
    {
        String val = "";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT " + DEFINITION_FIELD + " FROM " + TABLE_NAME + " WHERE _id = ? ",new String[]{String.valueOf(id)} );

        if(cursor.getCount() == 1)
        {
            cursor.moveToFirst();
            val =  cursor.getString(0);
        }

        return val;
    }


}
