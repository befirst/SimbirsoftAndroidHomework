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

public class DateFragment extends Fragment implements IUpdatable {

    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
    private TextView tvDate;

    public DateFragment() {
        // Required empty public constructor
    }

    @Override
    public void update() {
        tvDate.setText(format.format(new Date()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_date, container, false);

        tvDate = view.findViewById(R.id.tvDate);
        update();

        // Inflate the layout for this fragment
        return view;
    }

}
