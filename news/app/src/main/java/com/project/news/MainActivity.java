package com.project.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public MyadapterClass adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("news apap");
        recyclerView = findViewById(R.id.rv_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyadapterClass(dataqueue(), getApplicationContext());
        recyclerView.setAdapter(adapter);
//        liner layout horizontal
//        LinearLayoutManager  layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
//        recyclerView.setLayoutManager(layoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @NonNull
    public ArrayList<model> dataqueue() {
        ArrayList<model> arr = new ArrayList<>();
        model m1 = new model();
        m1.setHeader("java");
        m1.setDesc("Java by eclicpes");
        m1.setImgName(R.drawable.java);
        arr.add(m1);

        model m2 = new model();
        m2.setHeader("C++");
        m2.setDesc("C++ by eclips");
        m2.setImgName(R.drawable.cpp);
        arr.add(m2);

        model m3 = new model();
        m3.setHeader("python");
        m3.setDesc("python by eclips");
        m3.setImgName(R.drawable.python);
        arr.add(m3);


        model m4 = new model();
        m4.setHeader("django");
        m4.setDesc("django by eclips");
        m4.setImgName(R.drawable.django);
        arr.add(m4);

        model M5 = new model();
        M5.setHeader("HTML");
        M5.setDesc("HTML by eclips");
        M5.setImgName(R.drawable.django);
        arr.add(M5);

        model M6 = new model();
        M6.setHeader("CSS");
        M6.setDesc("CSS by eclips");
        M6.setImgName(R.drawable.javasrcipt);
        arr.add(M5);

        model M7 = new model();
        M7.setHeader("javasrcipt");
        M7.setDesc("javasrcipt by eclips");
        M7.setImgName(R.drawable.javasrcipt);
        arr.add(M7);
        return arr;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        MenuItem item = menu.findItem(R.id.search_menu);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

            adapter.getFilter().filter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}