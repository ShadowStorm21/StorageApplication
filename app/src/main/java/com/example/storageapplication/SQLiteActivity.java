package com.example.storageapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class SQLiteActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {
    private EditText editTextWord,editTextDef;
    private Button buttonSave;
    private ListView listView;
    private DatabaseHelper databaseHelper;
    private Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_q_lite);

        editTextDef = findViewById(R.id.editTextDef);
        editTextWord = findViewById(R.id.editTextWord);
        buttonSave = findViewById(R.id.button2);
        listView = findViewById(R.id.listView);
        databaseHelper = new DatabaseHelper(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(SQLiteActivity.this, databaseHelper.findDefinition(id), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                databaseHelper.deleteRecord(id);
                Toast.makeText(SQLiteActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                getSupportLoaderManager().restartLoader(0,null,SQLiteActivity.this);
                return true;
            }
        });

        getSupportLoaderManager().initLoader(0,null,this);
        adapter = new Adapter(this,databaseHelper.getWordList(),0);
        listView.setAdapter(adapter);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextWord.getText().toString().isEmpty() || editTextDef.getText().toString().isEmpty())
                {
                    return;
                }
                String word = editTextWord.getText().toString();
                String def = editTextDef.getText().toString();
                databaseHelper.saveRecord(word,def);
                editTextDef.setText("");
                editTextWord.setText("");
                getSupportLoaderManager().restartLoader(0,null,SQLiteActivity.this);


            }
        });


    }


    @NonNull
    @Override
    public Loader onCreateLoader(int id, @Nullable Bundle args) {
        return new MyLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, Object data) {
        adapter.swapCursor((Cursor) data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {
        adapter.swapCursor(null);
    }
}