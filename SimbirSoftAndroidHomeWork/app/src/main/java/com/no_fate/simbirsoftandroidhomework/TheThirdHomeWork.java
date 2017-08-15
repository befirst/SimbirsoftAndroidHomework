package com.no_fate.simbirsoftandroidhomework;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TheThirdHomeWork extends AppCompatActivity implements View.OnClickListener{

    private final static int HEX_NUMBERS_COUNT = 6;
    private final static int CODE_ERROR = -1;
    private final static int CODE_GOOD = 1;

    private TextView tvHEXColor;
    private Button bConfirm;
    private int hexColor;

    public static Intent GetIntent(Context context) {
        return new Intent(context, TheThirdHomeWork.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_third_home_work);

        tvHEXColor = (TextView) findViewById(R.id.tvHEXColor);
        bConfirm = (Button) findViewById(R.id.bConfirm);
        bConfirm.setOnClickListener(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onClick(View view) {

        // почему досуп через MainActivity и через статическую переменную? //
        // здесь hex_numbers_count лучше не выносить в ресурсы //
        // выделил потому что подумал что вызов getResources() это труднозатратно
//        if(tvHEXColor.getText().length() < getResources().getInteger(R.integer.hex_numbers_count)){
//            showToastNotEnoughSymbols();
//            return;
//        }

        if(tvHEXColor.getText().length() < HEX_NUMBERS_COUNT){
            showToastNotEnoughSymbols();
            return;
        }

        saveColorInExtra(tryParseColor());

        finish();
    }

    private int tryParseColor(){
        try{
            hexColor = Color.parseColor("#" + tvHEXColor.getText());
        } catch(Exception e){
            showToastColorNotExist();
            return CODE_ERROR;
        }
        return CODE_GOOD;
    }

    private void saveColorInExtra(int previousOperationCode){
        if(previousOperationCode == CODE_GOOD){
            Intent intent = new Intent();
            intent.putExtra(TheSecondHomeWork.KEY_CODE_HEXCOLOR, hexColor);
            setResult(RESULT_OK, intent);
        }
    }

    private void showToastNotEnoughSymbols(){
        Toast.makeText(
                getApplicationContext(),
                getResources().getString(
                        R.string.failure_not_enough_symbols,
                        HEX_NUMBERS_COUNT
                ),
                Toast.LENGTH_SHORT
        ).show();
    }

    private void showToastColorNotExist(){
        Toast.makeText(
                getApplicationContext(),
                R.string.failure_invalid_string,
                Toast.LENGTH_SHORT
        ).show();
    }
}
