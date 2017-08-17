package com.no_fate.simbirsoftandroidhomework;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Denis on 16.08.2017.
 */

enum PolicyType{
    CNCInsurance, CMTPL,  PropertyInsurance,  HealthInsurance
}

public class InsurancePolicy {
    private final static int MILLISECONDS_PER_DAY = 86400000;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    private PolicyType type;
    private String policyNumber;
    private PolicyObject policyObject;
    private Calendar beginningDate;
    private Calendar deadline;

    private double distanceBetweenBeginningAndDeadlineInDays;

    public InsurancePolicy(PolicyType type, String policyNumber, PolicyObject policyObject,
                           GregorianCalendar beginningDate, GregorianCalendar deadline) {
        this.type = type;
        this.policyNumber = policyNumber;
        this.policyObject = policyObject;
        this.beginningDate = beginningDate;
        this.deadline = deadline;

        updateDistance();
    }

    public PolicyType getType() {
        return type;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getPolicyObject() {
        return policyObject.getFormattedInfo();
    }

    public int getProgress(Calendar nowTime){
        double result = (getDistanceFromNowInDays(nowTime)/distanceBetweenBeginningAndDeadlineInDays) * 100;
        return (int)result;
    }

    public int getDistanceInDays(Calendar nowTime){
        double result = distanceBetweenBeginningAndDeadlineInDays -  getDistanceFromNowInDays(nowTime);
        return (int)result;
    }

    public String getFormattedBeginningDate() {
        return dateFormat.format(beginningDate.getTime());
    }

    public String getFormattedDeadline() {
        return dateFormat.format(deadline.getTime());
    }

    public void buyPolicy(Calendar nowTime){
        final int ADDED_DAYS = 30;
        beginningDate = nowTime;
        deadline.setTimeInMillis((nowTime.getTimeInMillis() + ((long)ADDED_DAYS * (long)MILLISECONDS_PER_DAY)));
        updateDistance();
    }

    private int getDistanceFromNowInDays(Calendar nowTime){
        long distanceFromNow = nowTime.getTimeInMillis() - beginningDate.getTimeInMillis();
        double distanceFromNowInDays = distanceFromNow / MILLISECONDS_PER_DAY;
        return (int)distanceFromNowInDays;
    }

    private void updateDistance(){
        long distanceBetweenBeginningAndDeadline =
                deadline.getTimeInMillis() - beginningDate.getTimeInMillis();
        distanceBetweenBeginningAndDeadlineInDays =
                distanceBetweenBeginningAndDeadline / MILLISECONDS_PER_DAY;
    }
}
