package com.example.ex092;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnCreateContextMenuListener {
    ListView lv;
    TextView tv;
    boolean type;
    double x, y;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lv = findViewById(R.id.lv);
        tv = findViewById(R.id.tv);
        Intent gi = getIntent();
        type=gi.getBooleanExtra("type", true);
        x=gi.getDoubleExtra("x", 0);
        y=gi.getDoubleExtra("y", 0);
        String[] arr = gi.getStringArrayExtra("arr");
        ArrayAdapter<String> adp = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arr);
        lv.setAdapter(adp);
        lv.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add("index");
        menu.add("sum");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String oper = item.getTitle().toString();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;

        if (oper.equals("sum")) {
            double sum;
            if (type){
                sum = ((2*x +index*y)*(index+1)) /2;
            }
            else{
                sum= x * (((Math.pow(y,index+1))-1)/(y-1));
            }
            tv.setText(String.format("%s", sum));
        }
        else{
            tv.setText("x="+(index+1));
        }
        return super.onContextItemSelected(item);
    }
}