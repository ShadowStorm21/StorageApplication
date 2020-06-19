package com.example.storageapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileWriteActivity extends AppCompatActivity {
    private static final String FILENAME = "DATA.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_write);

        final EditText editText = findViewById(R.id.editTextdata);
        Button write = findViewById(R.id.buttonWrite);
        final Button read = findViewById(R.id.buttonRead);
        final TextView textView = findViewById(R.id.textViewRead);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = openFileOutput(FILENAME,MODE_PRIVATE);
                    fileOutputStream.write(editText.getText().toString().getBytes());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {

                    if(fileOutputStream != null)
                    {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BufferedReader bufferedReader = null;

                try {
                    FileInputStream fileInputStream = openFileInput(FILENAME);
                    bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                    String line = null;
                    StringBuilder stringBuilder = new StringBuilder();

                    while ((line = bufferedReader.readLine()) != null)
                    {
                        stringBuilder.append(line);
                    }
                    textView.setText(stringBuilder.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if(bufferedReader != null)
                    {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
        });

        final EditText editText1 = findViewById(R.id.editTextText);
        Button buttonWrite = findViewById(R.id.button4);
        Button buttonRead = findViewById(R.id.button5);
        final TextView textView1 = findViewById(R.id.textView5);

        buttonWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isExternalStorageWriteable())
                {
                    try {
                        File file = new File(Environment.getExternalStorageDirectory(),FILENAME);
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(editText1.getText().toString().getBytes());
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(FileWriteActivity.this, "Error Writing to external Memory!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isExternalStorageReadable())
                {
                    StringBuilder stringBuilder = new StringBuilder();
                    BufferedReader bufferedReader = null;
                    try {
                        File file = new File(Environment.getExternalStorageDirectory(),FILENAME);
                        FileInputStream fileInputStream = new FileInputStream(file);
                        bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                        String line = null;

                        while ((line = bufferedReader.readLine()) != null)
                        {
                            stringBuilder.append(line+"\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally {

                        if(bufferedReader != null)
                        {
                            try {
                                bufferedReader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    textView1.setText(stringBuilder.toString());
                }
                else
                {
                    Toast.makeText(FileWriteActivity.this, "Error Reading External Memory!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public boolean isExternalStorageWriteable()
    {
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
        {
            return true;
        }

        return false;
    }
    public boolean isExternalStorageReadable()
    {
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState()))
        {
            return true;
        }

        return false;
    }
}