package com.project.weather_app___;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class NewsView extends AppCompatActivity {

    TextView title, content, publisher, date;
    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view_point);
        title = findViewById(R.id.idNVTitle);
        content = findViewById(R.id.idNVContent);
        publisher = findViewById(R.id.idNVPublisher);
        date = findViewById(R.id.idNVDate);
        icon = findViewById(R.id.idNIcon);
        title.setText(getIntent().getStringExtra("title"));
        content.setText(getIntent().getStringExtra("content"));
        publisher.setText(getIntent().getStringExtra("publisher"));
        date.setText(getIntent().getStringExtra("date"));
        Picasso.get().load(getIntent().getStringExtra("Img_url")).into(icon);

    }
}