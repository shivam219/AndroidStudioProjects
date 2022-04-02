package com.project.flash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"First",Toast.LENGTH_LONG);

        TextView tv = findViewById(R.id.tvfa);
        tv.setOnClickListener((V)->{
            Intent i = new Intent(MainActivity.this,SecondActivity.class);
            startActivity(i);
        } );
    }
    protected  void onStart() {
        super.onStart();
        Toast.makeText(this,"first",Toast.LENGTH_LONG);
    }
    protected  void onPause() {

        super.onPause();
    }
    protected  void onResume() {

        super.onResume();
    }
    protected  void onStop() {

        super.onStop();
    }
    protected void onRestart() {

        super.onRestart();
    }
    protected void onDestroy() {
        super.onDestroy();
    }
}