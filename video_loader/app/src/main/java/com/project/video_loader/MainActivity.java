package com.project.video_loader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ViewPager2 veViewPager2;
    ArrayList<videomodel> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        veViewPager2 = findViewById(R.id.view_pager2);
        al = new ArrayList<>();
        videomodel ob1 = new videomodel();
        ob1.setUrl("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
        ob1.setTitle("cartoon");
        ob1.setDesc("cartoon");
        al.add(ob1);

        videomodel ob2 = new videomodel();
        ob2.setUrl("https://cdn.videvo.net/videvo_files/video/free/2019-03/large_watermarked/181004_10_LABORATORIES-SCIENCE_23_preview.mp4");
        ob2.setTitle("cartoon");
        ob2.setDesc("cartoon");
        al.add(ob2);
        videomodel ob3 = new videomodel();
        ob3.setUrl("https://cdn.videvo.net/videvo_files/video/premium/video0224/large_watermarked/MR_Stock%20Footage%20MR%20(1871)_preview.mp4");
        ob3.setTitle("cartoon");
        ob3.setDesc("cartoon");
        al.add(ob3);
        videomodel ob4 = new videomodel();
        ob4.setUrl("https://cdn.videvo.net/videvo_files/video/premium/video0230/large_watermarked/MR_Stock%20Footage%20MR%20(601)_preview.mp4");
        ob4.setTitle("cartoon");
        ob4.setDesc("cartoon");
        al.add(ob4);
        videomodel ob5 = new videomodel();
        ob5.setUrl("https://cdn.videvo.net/videvo_files/video/premium/2020-08/large_watermarked/200309_02_atomic%20archive_collection_2_010_preview.mp4");
        ob5.setTitle("cartoon");
        ob5.setDesc("cartoon");
        al.add(ob5);
        videomodel ob6 = new videomodel();
        ob6.setUrl("https://cdn.videvo.net/videvo_files/video/free/2020-04/large_watermarked/200401_Medical%206_03_preview.mp4");
        ob6.setTitle("cartoon");
        ob6.setDesc("cartoon");
        al.add(ob6);

        veViewPager2.setAdapter(new adaptervideo(al));
    }
}