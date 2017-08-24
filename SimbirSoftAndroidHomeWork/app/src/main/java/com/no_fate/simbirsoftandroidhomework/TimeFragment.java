package com.no_fate.simbirsoftandroidhomework;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeFragment extends Fragment implements IUpdatable{

    private final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    private TextView tvTime;

    public TimeFragment() {
        // Required empty public constructor
    }

    @Override
    public void update(){
        tvTime.setText(format.format(new Date()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_time, container, false);

        tvTime = view.findViewById(R.id.tvTime);
        update();

        // Inflate the layout for this fragment
        return view;
    }

}
