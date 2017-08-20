package com.no_fate.simbirsoftandroidhomework;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static Resources res;

    private TextView tAdditionalInfo;
    private Button[] buttonsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tAdditionalInfo = (TextView) findViewById(R.id.tAdditionalInfo);
        buttonsArray = new Button[]{
                (Button) findViewById(R.id.bTheSixthHWActivityOpen),
                (Button) findViewById(R.id.bTheFifthHWActivityOpen),
                (Button) findViewById(R.id.bTheFourthHWActivityOpen),
                (Button) findViewById(R.id.bTheSecondAndTheThirdHWActivityOpen),
        };
        for (Button i: buttonsArray) {
            i.setOnClickListener(this);
        }

        res = getResources();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bTheSixthHWActivityOpen:
                tAdditionalInfo.setText(res.getString(R.string.empty_activity, "6"));
                break;
            case R.id.bTheFifthHWActivityOpen:
                TheFifthHomeWork.start(MainActivity.this);
                break;
            case R.id.bTheFourthHWActivityOpen:
                TheFourthHomeWork.start(MainActivity.this);
                break;
            case R.id.bTheSecondAndTheThirdHWActivityOpen:
                TheSecondHomeWork.start(MainActivity.this);
                break;
        }
    }
}
