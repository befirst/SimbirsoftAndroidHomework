package com.no_fate.simbirsoftandroidhomework;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class TheFourthHomeWork extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Mint);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_fourth_home_work);

        Toolbar customToolBar = (Toolbar) findViewById(R.id.toolBarCustom);
        setSupportActionBar(customToolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, TheFourthHomeWork.class);
        //starter.putExtra();
        context.startActivity(starter);
    }
}
