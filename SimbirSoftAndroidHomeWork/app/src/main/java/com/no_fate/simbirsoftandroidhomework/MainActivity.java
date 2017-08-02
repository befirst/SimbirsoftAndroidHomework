package com.no_fate.simbirsoftandroidhomework;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tAdditionalInfo;
    Button[] buttonsArray;
    Intent intentChangeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tAdditionalInfo = (TextView) findViewById(R.id.tAdditionalInfo);
        buttonsArray = new Button[]{
                (Button) findViewById(R.id.bTheSixthHWActivityOpen),
                (Button) findViewById(R.id.bTheFifthHWActivityOpen),
                (Button) findViewById(R.id.bTheFourthHWActivityOpen),
                (Button) findViewById(R.id.bTheThirdHWActivityOpen),
                (Button) findViewById(R.id.bTheSecondHWActivityOpen),
        };
        for (Button i: buttonsArray) {
            i.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Resources res = getResources();
        switch (v.getId()) {
            case R.id.bTheSixthHWActivityOpen:
                tAdditionalInfo.setText(res.getString(R.string.empty_activity, "6"));
                break;
            case R.id.bTheFifthHWActivityOpen:
                tAdditionalInfo.setText(res.getString(R.string.empty_activity, "5"));
                break;
            case R.id.bTheFourthHWActivityOpen:
                tAdditionalInfo.setText(res.getString(R.string.empty_activity, "4"));
                break;
            case R.id.bTheThirdHWActivityOpen:
                tAdditionalInfo.setText(res.getString(R.string.empty_activity, "3"));
                break;
            case R.id.bTheSecondHWActivityOpen:
                intentChangeActivity = new Intent(MainActivity.this, TheSecondHomeWork.class);
                startActivity(intentChangeActivity);
                break;
        }
    }
}
