package com.no_fate.simbirsoftandroidhomework;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Random;

public class RandomPictureFragment extends Fragment implements IUpdatable {

    private ImageView ivRandom;
    private final int[] images={
            R.drawable.icon_car,
            R.drawable.icon_car_kasko,
            R.drawable.icon_key,
            R.drawable.icon_medec
    };

    public RandomPictureFragment() {
        // Required empty public constructor
    }

    @Override
    public void update() {
        Random random = new Random();
        int index = random.nextInt(images.length);
        ivRandom.setImageResource(images[index]);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_random_picture, container, false);

        ivRandom = view.findViewById(R.id.ivRandom);
        update();

        // Inflate the layout for this fragment
        return view;
    }
}
