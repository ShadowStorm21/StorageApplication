package com.example.storageapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> stringList;
    private TextView t1,t2,t3;
    private Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stringList = new ArrayList<>();
        stringList.add("Cat");
        stringList.add("Dog");
        stringList.add("Mouse");

        t1 = findViewById(R.id.textView3);
        t2 = findViewById(R.id.textView);
        t3 = findViewById(R.id.textView2);
        aSwitch = findViewById(R.id.switch1);

        if(savedInstanceState == null) {

            t1.setText(stringList.get(0));
            t2.setText(stringList.get(1));
            t3.setText(stringList.get(2));
        }


    }



    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("array",stringList);
        outState.putBoolean("switch",aSwitch.isChecked());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        stringList = savedInstanceState.getStringArrayList("array");


           t1.setText(stringList.get(0));
           t2.setText(stringList.get(1));
           t3.setText(stringList.get(2));
           aSwitch.setChecked(savedInstanceState.getBoolean("switch"));


    }
}