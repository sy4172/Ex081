package com.example.ex081;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstObject, distance;
    Switch type;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstObject = findViewById(R.id.firstObject);
        distance = findViewById(R.id.distance);
        type = findViewById(R.id.type);
    }

    public void showTheSeries(View view) {
        if (firstObject.getText().toString().isEmpty() || distance.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show();
        }
        else{
            flag = type.isChecked();
            Intent si = new Intent(this, showTheObjects.class);
            si.putExtra("firstObject", firstObject.getText().toString());
            si.putExtra("distance", distance.getText().toString());
            si.putExtra("type",flag);
            startActivity(si);
        }
    }
}