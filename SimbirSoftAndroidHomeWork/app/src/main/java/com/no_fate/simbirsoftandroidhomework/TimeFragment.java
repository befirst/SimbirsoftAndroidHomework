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


/**
 * A simple {@link Fragment} subclass.
 */
public class TimeFragment extends Fragment {

    public TimeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_time, container, false);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String myDate = format.format(new Date());

        TextView tvTime = view.findViewById(R.id.tvTime);
        tvTime.setText(myDate);

        // Inflate the layout for this fragment
        return view;
    }

}
