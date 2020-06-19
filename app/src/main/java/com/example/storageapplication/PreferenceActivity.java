package com.example.storageapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class PreferenceActivity extends AppCompatActivity {
    private EditText editText, editText2;
    private CheckBox checkBox,checkBox2;
    private RadioButton radioButton,radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        radioButton = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);


    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences("MyData",MODE_PRIVATE);
        if(!editText.getText().toString().isEmpty() || !editText2.getText().toString().isEmpty()) {
            sharedPreferences.edit().putString("EDITTEXT", editText.getText().toString()).commit();
            sharedPreferences.edit().putString("EDITTEXT2", editText2.getText().toString()).commit();
            sharedPreferences.edit().putBoolean("CHECKBOX", checkBox.isChecked()).commit();
            sharedPreferences.edit().putBoolean("CHECKBOX2", checkBox2.isChecked()).commit();
            sharedPreferences.edit().putBoolean("RADIOBUTTON", radioButton.isChecked()).commit();
            sharedPreferences.edit().putBoolean("RADIOBUTTON2", radioButton2.isChecked()).commit();

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("MyData",MODE_PRIVATE);
        editText.setText(sharedPreferences.getString("EDITTEXT",null));
        editText2.setText(sharedPreferences.getString("EDITTEXT2",null));
        checkBox.setChecked(sharedPreferences.getBoolean("CHECKBOX",false));
        checkBox2.setChecked(sharedPreferences.getBoolean("CHECKBOX2",false));
        radioButton.setChecked(sharedPreferences.getBoolean("RADIOBUTTON",false));
        radioButton2.setChecked(sharedPreferences.getBoolean("RADIOBUTTON2",false));
    }
}