package com.example.matthieu.sidenav;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle storyTagBundle = getIntent().getExtras();
        ArrayList<Item> items = storyTagBundle.getParcelableArrayList("test");
        String themeName = storyTagBundle.getString("name");
        getSupportActionBar().setTitle(themeName);

        GridView gridview = (GridView) findViewById(R.id.grid);
        gridview.setAdapter(new ItemAdapter(this,items));

    }

}
