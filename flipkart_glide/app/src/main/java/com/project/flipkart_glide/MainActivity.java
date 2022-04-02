package com.project.flipkart_glide;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.img);
        t1 = findViewById(R.id.t1);

        Glide.with(this).load("https://th.bing.com/th/id/OIP.FKseL9s8PCorH9eHJ0WG0gHaE8?pid=ImgDet&rs=1").into(img);
    }
}