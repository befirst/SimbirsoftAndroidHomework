package com.no_fate.simbirsoftandroidhomework;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class TheFourthHomeWork extends AppCompatActivity {

    private List<InsurancePolicy> policies;

    public static void start(Context context) {
        Intent starter = new Intent(context, TheFourthHomeWork.class);
        context.startActivity(starter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_custom, menu);
        return true;
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
            getSupportActionBar().setDisplayShowTitleEnabled(false);
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
                new GregorianCalendar(2017, 9, 11)
        ));
        policies.add(new InsurancePolicy(
                PolicyType.HealthInsurance,
                "6213564SFG21DFG5DF2D3FG",
                new FIO("Semenov", "Semen", "Semenovich"),
                new GregorianCalendar(2013, 1, 28),
                new GregorianCalendar(2017, 9, 16)
        ));
        policies.add(new InsurancePolicy(
                PolicyType.PropertyInsurance,
                "SFG1F32GD45FGD31",
                new HomeAddress("Ulyanovsk city", "Telmana street", "22", "13"),
                new GregorianCalendar(2016, 12, 28),
                new GregorianCalendar(2017, 8, 1)
        ));
        policies.add(new InsurancePolicy(
                PolicyType.PropertyInsurance,
                "38753HG3583H9G83GN",
                new HomeAddress("Ulyanovsk city", "Telmana street", "3", "46"),
                new GregorianCalendar(2013, 1, 28),
                new GregorianCalendar(2017, 9, 20)
        ));
        policies.add(new InsurancePolicy(
                PolicyType.CNCInsurance,
                "MGERGNERG8E9RG8ERNGSFGL",
                new Automobile("UAZ", "Patriot"),
                new GregorianCalendar(2013, 1, 28),
                new GregorianCalendar(2017, 9, 20)
        ));
        policies.add(new InsurancePolicy(
                PolicyType.HealthInsurance,
                "KDJHKSDFJG90485Y04TIHN",
                new FIO("Petrov", "Petr", "Petrovich"),
                new GregorianCalendar(2017, 6, 11),
                new GregorianCalendar(2017, 7, 25)
        ));
        policies.add(new InsurancePolicy(
                PolicyType.CNCInsurance,
                "DFGSDHJNGJHF0590R4569874FGMNH95",
                new Automobile("Mazda", "RX-8"),
                new GregorianCalendar(2017, 0, 1),
                new GregorianCalendar(2018, 0, 1)
        ));
        policies.add(new InsurancePolicy(
                PolicyType.HealthInsurance,
                "B475G9R05YMHNFKGN0",
                new FIO("Vasilyev", "Vasiliy", "Vasilyevich"),
                new GregorianCalendar(2000, 5, 14),
                new GregorianCalendar(2017, 7, 22)
        ));
        policies.add(new InsurancePolicy(
                PolicyType.PropertyInsurance,
                "JSFG9094NR90GNFGN",
                new HomeAddress("Moskva city", "Delovoy centr street", "1000", "77"),
                new GregorianCalendar(2013, 1, 28),
                new GregorianCalendar(2017, 7, 30)
        ));
        policies.add(new InsurancePolicy(
                PolicyType.CNCInsurance,
                "JH049N49NFGN9JJ",
                new Automobile("Tesla", "Model S"),
                new GregorianCalendar(2013, 1, 28),
                new GregorianCalendar(2017, 7, 29)
        ));
    }

}
