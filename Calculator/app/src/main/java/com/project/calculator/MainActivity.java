package com.project.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText etFv,etSv;
    TextView tvRes;
    Button btnAddition, btnSubtraction,btnMultiplication,btnDivision , btnClear,btnShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etFv = findViewById(R.id.etFirstValue);
        etSv = findViewById(R.id.etSecondValue);

        tvRes  = findViewById(R.id.tvResult);


        btnAddition = findViewById(R.id.btnAdd);
        btnSubtraction = findViewById(R.id.btnSub);
        btnMultiplication= findViewById(R.id.btnMul);
        btnDivision = findViewById(R.id.btnDiv);
        btnClear = findViewById(R.id.btnClear);
        btnShow =findViewById(R.id.btnShow);

        btnShow.setOnClickListener((V)->{
            Toast t = Toast.makeText(MainActivity.this, "This is calulator" ,Toast.LENGTH_LONG );
            t.setGravity(Gravity.CENTER,0,500);
            t.show();

        });

        btnAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(etFv.getText().toString() =="" & etSv.getText().toString() =="" )) {
                    int ftValue = Integer.parseInt(String.valueOf(etFv.getText()).toString());
                    int sdValue = Integer.parseInt(String.valueOf(etSv.getText()).toString());
                    int sum = ftValue + sdValue;
                    tvRes.setText(String.valueOf(sum));
                }
            }
        });
        btnSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(etFv.getText().toString() =="" & etSv.getText().toString() =="" )) {
                    int ftValue = Integer.parseInt(String.valueOf(etFv.getText()).toString());
                    int sdValue = Integer.parseInt(String.valueOf(etSv.getText()).toString());
                    int sum = ftValue - sdValue;
                    tvRes.setText(String.valueOf(sum));
                }
            }
        });

        btnMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(etFv.getText().toString() =="" & etSv.getText().toString() =="" )) {
                    int ftValue = Integer.parseInt(String.valueOf(etFv.getText()).toString());
                    int sdValue = Integer.parseInt(String.valueOf(etSv.getText()).toString());
                    int sum = ftValue * sdValue;
                    tvRes.setText(String.valueOf(sum));
                }
            }
        });

        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(etFv.getText().toString() =="" & etSv.getText().toString() =="" )) {

                    int ftValue=  Integer.parseInt(String.valueOf(etFv.getText()).toString() );
                    int sdValue=  Integer.parseInt(String.valueOf(etSv.getText()).toString() );
                    if(!(sdValue ==0) ){
                        if(ftValue<sdValue) {
                            double sum = ftValue / ( sdValue * 1.0);
                            tvRes.setText(String.valueOf(sum));
                        }
                        else {
                            int sum = ftValue / sdValue;
                            tvRes.setText(String.valueOf(sum));
                        }
                    }else{
                        tvRes.setText("Dividing no by zero");
                    }
                }
            }
        });



        btnClear.setOnClickListener( (V)->{
            tvRes.setText("Result "); etFv.setText(""); etSv.setText("");
        });

    }

}