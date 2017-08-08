package com.no_fate.simbirsoftandroidhomework;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TheSecondHomeWork extends AppCompatActivity implements View.OnClickListener{
    private TextView tTitle;
    private TextView tFiller;
    private Button bCherry;
    private Button bNight;
    private Button bDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_second_home_work);

        tTitle = (TextView) findViewById(R.id.tTitle);
        tFiller = (TextView) findViewById(R.id.tMainText);
        bCherry = (Button) findViewById(R.id.bCherry);
        bCherry.setOnClickListener(this);
        bNight = (Button) findViewById(R.id.bNight);
        bNight.setOnClickListener(this);
        bDefault = (Button) findViewById(R.id.bDefault);
        bDefault.setOnClickListener(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    // дублирующуюся логику можно вынести в отдельный метод
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bCherry:
                tTitle.setBackgroundColor(getResources().getColor(R.color.cherry));
                tTitle.setTextColor(Color.BLACK);
                tFiller.setBackgroundColor(getResources().getColor(R.color.cherry));
                tFiller.setTextColor(Color.BLACK);
                showToastColorChanged();
                break;
            case R.id.bNight:
                tTitle.setBackgroundColor(Color.BLACK);
                tTitle.setTextColor(Color.WHITE);
                tFiller.setBackgroundColor(Color.BLACK);
                tFiller.setTextColor(Color.WHITE);
                showToastColorChanged();
                break;
            case R.id.bDefault:
                tTitle.setBackgroundColor(Color.TRANSPARENT);
                tTitle.setTextColor(Color.BLACK);
                tFiller.setBackgroundColor(Color.TRANSPARENT);
                tFiller.setTextColor(Color.BLACK);
                showToastColorChanged();
                break;
        }
    }

    public static void Start(Context context) {
        Intent starter = new Intent(context, TheSecondHomeWork.class);
        context.startActivity(starter);
    }

    // переменная toast лишняя
    // строка должна быть в ресурсах
    private void showToastColorChanged(){
        Toast toast = Toast.makeText(getApplicationContext(),"Done", Toast.LENGTH_LONG);
        toast.show();
    }
}
