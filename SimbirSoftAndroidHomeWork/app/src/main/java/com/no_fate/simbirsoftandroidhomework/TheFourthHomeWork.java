package com.no_fate.simbirsoftandroidhomework;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Date;

public class TheFourthHomeWork extends AppCompatActivity {

    private List<InsurancePolicy> policies;

    public static void start(Context context) {
        Intent starter = new Intent(context, TheFourthHomeWork.class);
        //starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Mint);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_fourth_home_work);

        initializePolicies();

        RecyclerView rvPolicy = (RecyclerView)findViewById(R.id.rvPolicy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvPolicy.setLayoutManager(linearLayoutManager);
        PolicyAdapter policyAdapter = new PolicyAdapter(this, policies);
        rvPolicy.setAdapter(policyAdapter);

        Toolbar customToolBar = (Toolbar) findViewById(R.id.toolBarCustom);
        setSupportActionBar(customToolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initializePolicies(){
        policies = new ArrayList<>();
        policies.add(new InsurancePolicy(
                PolicyType.CMTPL,
                "21321354646513EFSGSEGSF",
                new Automobile("Toyota", "Corolla"),
                new GregorianCalendar(2013, 1, 28),
                new GregorianCalendar(2017,10,20)
        ));
        policies.add(new InsurancePolicy(
                PolicyType.HealthInsurance,
                "6213564SFG21DFG5DF2D3FG",
                new FIO("Semenov", "Semen", "Semenovich"),
                new GregorianCalendar(2013, 1, 28),
                new GregorianCalendar(2017,10,20)
        ));
        policies.add(new InsurancePolicy(
                PolicyType.PropertyInsurance,
                "SFG1F32GD45FGD31",
                new HomeAddress("Russia", "Telmana", "22", "13"),
                new GregorianCalendar(2013, 1, 28),
                new GregorianCalendar(2017,10,20)
        ));
    }

}
