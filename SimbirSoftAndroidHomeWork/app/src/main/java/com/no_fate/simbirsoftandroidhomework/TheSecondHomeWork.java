package com.no_fate.simbirsoftandroidhomework;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;

public class TheSecondHomeWork extends AppCompatActivity implements View.OnClickListener{

    public final static String KEY_CODE_HEXCOLOR = "hex_color";
    private final int REQUEST_CODE_HEXCOLOR = 1;

    public final static String KEY_CODE_PICKED_IMAGE = "picked_image";
    private final static int REQUEST_CODE_GET_IMAGE = 2;

    private TextView tTitle;
    private TextView tFiller;

    private ImageView ivImage;

    private Button bCherry;
    private Button bNight;
    private Button bDefault;
    private Button bCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_second_home_work);

        tTitle = (TextView) findViewById(R.id.tTitle);
        tFiller = (TextView) findViewById(R.id.tMainText);

        ivImage = (ImageView) findViewById(R.id.imageView);
        ivImage.setOnClickListener(this);

        bCherry = (Button) findViewById(R.id.bCherry);
        bCherry.setOnClickListener(this);
        bNight = (Button) findViewById(R.id.bNight);
        bNight.setOnClickListener(this);
        bDefault = (Button) findViewById(R.id.bDefault);
        bDefault.setOnClickListener(this);
        bCustom = (Button) findViewById(R.id.bCustom);
        bCustom.setOnClickListener(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(data != null){
                switch (requestCode){
                    case REQUEST_CODE_HEXCOLOR:
                        changeTextViewsBackgroundColor(
                                data.getIntExtra(KEY_CODE_HEXCOLOR, Color.TRANSPARENT)
                        );
                        break;
                    case REQUEST_CODE_GET_IMAGE:
                        try {
                            final Uri imageUri = data.getData();
                            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            ivImage.setImageBitmap(selectedImage);
                        } catch (Exception e) {
                            showToastPastingImageError();
                        }
                        break;
                }
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bCherry:
                changeTextViewsColors(getResources().getColor(R.color.cherry), Color.BLACK);
                showToastColorChanged();
                break;
            case R.id.bNight:
                changeTextViewsColors(Color.BLACK, Color.WHITE);
                showToastColorChanged();
                break;
            case R.id.bDefault:
                changeTextViewsColors(Color.TRANSPARENT, Color.BLACK);
                showToastColorChanged();
                break;
            case R.id.bCustom:
                startActivityForResult(
                        TheThirdHomeWork.GetIntent(TheSecondHomeWork.this),
                        REQUEST_CODE_HEXCOLOR
                );
                break;
            case R.id.imageView:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);

                Uri uri = Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath());
                intent.setDataAndType(uri, "image/*");

                startActivityForResult(intent, REQUEST_CODE_GET_IMAGE);
                break;
        }
    }

    public static void Start(Context context) {
        Intent starter = new Intent(context, TheSecondHomeWork.class);
        context.startActivity(starter);
    }

    private void showToastColorChanged(){
        Toast.makeText(getApplicationContext(), R.string.done, Toast.LENGTH_SHORT).show();
    }

    private void showToastPastingImageError(){
        Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_SHORT).show();
    }

    private void changeTextViewsColors(int backgroundColor, int textColor){
        tTitle.setBackgroundColor(backgroundColor);
        tTitle.setTextColor(textColor);
        tFiller.setBackgroundColor(backgroundColor);
        tFiller.setTextColor(textColor);
    }

    private void changeTextViewsBackgroundColor(int backgroundColor){
        tTitle.setBackgroundColor(backgroundColor);
        tFiller.setBackgroundColor(backgroundColor);
    }
}
