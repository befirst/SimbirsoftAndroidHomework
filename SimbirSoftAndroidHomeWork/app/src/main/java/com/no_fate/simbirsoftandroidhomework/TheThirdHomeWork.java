package com.no_fate.simbirsoftandroidhomework;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TheThirdHomeWork extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_third_home_work);
    }

    public static void Start(Context context) {
        Intent starter = new Intent(context, TheThirdHomeWork.class);
        //starter.putExtra();
        context.startActivity(starter);
    }
}
