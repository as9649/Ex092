package com.example.ex092;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * @author Eden Shvartz
 * @since 10 /01/23
 */
public class MainActivity extends AppCompatActivity {
    RadioButton rB1, rB2;
    EditText eT1, eT2;

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        rB1=findViewById(R.id.rB1);
        rB2=findViewById(R.id.rB2);
        eT1=findViewById(R.id.eT1);
        eT2=findViewById(R.id.eT2);
        btn1=findViewById(R.id.btn1);



        btn1.setOnClickListener(view -> {
            if (!rB1.isChecked() && !rB2.isChecked())
                Toast.makeText(MainActivity.this, "You must choose...", Toast.LENGTH_SHORT).show();
            else {
                String st1 = eT1.getText().toString();
                String st2 = eT2.getText().toString();
                if (st1.matches("-?\\d+(\\.\\d+)?") && st2.matches("-?\\d+(\\.\\d+)?")) {
                    double x = Double.parseDouble(st1);
                    double y = Double.parseDouble(st2);
                    String[] arr = new String[20];
                    arr[0] = x + "";
                    boolean type;
                    if (rB2.isChecked()) {
                        for (int i = 1; i < arr.length; i++)
                            arr[i] = String.format("%s", (x * Math.pow(y, i)));
                        type=false;}
                    else {
                        for (int i = 1; i < arr.length; i++)
                            arr[i] = (x + y * (i)) + "";
                        type=true;}
                    Intent si=new Intent(this, MainActivity2.class);
                    si.putExtra("x", x);
                    si.putExtra("y", y);
                    si.putExtra("arr", arr);
                    si.putExtra("type", type);
                    startActivity(si);
                }
                else Toast.makeText(this, "Enter a number", Toast.LENGTH_SHORT).show();
            }
        });
    }
}