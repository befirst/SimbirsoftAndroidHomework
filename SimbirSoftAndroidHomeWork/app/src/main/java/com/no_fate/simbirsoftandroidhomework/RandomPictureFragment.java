package com.no_fate.simbirsoftandroidhomework;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class RandomPictureFragment extends Fragment {

    public RandomPictureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_random_picture, container, false);

        int[] images={R.drawable.icon_car,R.drawable.icon_car_kasko,R.drawable.icon_key,R.drawable.icon_medec};
        Random random = new Random();
        int index = random.nextInt(images.length);
        ImageView ivRandom = view.findViewById(R.id.ivRandom);
        ivRandom.setImageResource(images[index]);

        // Inflate the layout for this fragment
        return view;
    }

}
