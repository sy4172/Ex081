package com.example.ex081;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class showTheObjects extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String [] objects = new String[20];
    double distance, sum, first;
    boolean flag;
    String firstStr, distanceStr;
    TextView firstObject, d, pos, sumUntilPos;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_the_objects);

        firstObject = findViewById(R.id.firstObject);
        d = findViewById(R.id.d);
        pos = findViewById(R.id.pos);
        sumUntilPos = findViewById(R.id.sumUntilPos);
        lv = findViewById(R.id.lv);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(this);

        Intent gi = getIntent();
        first = Double.parseDouble(gi.getStringExtra("firstObject"));
        firstStr = String.valueOf(first);
        while (!(firstStr.endsWith(".")) && firstStr.contains(".") && firstStr.endsWith("0")){
            firstStr = firstStr.substring(0,String.valueOf(first).length()-2);
        }
        firstObject.setText(firstStr);

        distance = Double.parseDouble(gi.getStringExtra("distance"));
        distanceStr = String.valueOf(distance);
        while (!(distanceStr.endsWith(".")) && distanceStr.contains(".") && distanceStr.endsWith("0")){
            distanceStr = distanceStr.substring(0,String.valueOf(distance).length()-2);
        }
        d.setText(distanceStr);

        flag = gi.getBooleanExtra("type", true);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,objects);
        lv.setAdapter(adp);

        if (flag){ // Geometric series
            for (int i=0; i<objects.length; i++){
                objects[i] = String.valueOf(first*Math.pow(distance,(i+1)-1));
                while (!(objects[i].endsWith(".")) && objects[i].contains(".") && objects[i].endsWith("0")){
                    objects[i] = objects[i].substring(0,objects[i].length()-2);
                }
            }
        }
        else{ // Math series
            for (int i=0; i<objects.length;i++){
                objects[i] = String.valueOf(first+(distance*(i+1)-distance));
                while (!(objects[i].endsWith(".")) && objects[i].contains(".") && objects[i].endsWith("0")){
                    objects[i] = objects[i].substring(0,objects[i].length()-2);
                }
            }
        }
    }

    public void returnToMain(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        pos.setText(String.valueOf(position+1));
        if (flag){ // Geometric series
            for (int i=0; i<=position; i++){
                sum += Double.parseDouble(objects[i]);
            }
        }
        else{ // Math series
            for (int i=0; i<=position; i++){
                sum += Double.parseDouble(objects[i]);
            }
        }

        if ((sum - (int)sum > 0) &&  (sum - (int)sum < 1)){
            sumUntilPos.setText(String.valueOf(sum));
            sum = 0;
        }
        else{
            sumUntilPos.setText(String.valueOf((int) sum));
            sum = 0;
        }
    }
}