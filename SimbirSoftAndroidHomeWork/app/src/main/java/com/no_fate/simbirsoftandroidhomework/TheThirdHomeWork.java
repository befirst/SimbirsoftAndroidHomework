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

    private TextView tvHEXColor;
    // можно сделать локальной переменной
    private Button bConfirm;

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
        int hexColor;
        // почему досуп через MainActivity и через статическую переменную?
        // здесь hex_numbers_count лучше не выносить в ресурсы
        if(tvHEXColor.getText().length() < MainActivity.res.getInteger(R.integer.hex_numbers_count)){
            showToastNotEnoughSymbols();
            return;
        }

        // лучше вынести в отдельный метод, а не делать в onClick
        try{
            hexColor = Color.parseColor("#" + tvHEXColor.getText());
        } catch(Exception e){
            showToastColorNotExist();
            return;
        }

        // тоже в отдельный метод
        Intent intent = new Intent();
        intent.putExtra(TheSecondHomeWork.KEY_CODE_HEXCOLOR, hexColor);
        setResult(RESULT_OK, intent);
        finish();
    }

    // статические методы наверх класса
    public static Intent GetIntent(Context context) {
        return new Intent(context, TheThirdHomeWork.class);
    }

    private void showToastNotEnoughSymbols(){
        Toast.makeText(
                getApplicationContext(),
                MainActivity.res.getString(
                        R.string.failure_not_enough_symbols,
                        MainActivity.res.getInteger(R.integer.hex_numbers_count)
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
