package com.no_fate.simbirsoftandroidhomework;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Denis on 16.08.2017.
 */

enum PolicyType{
    CNCInsurance, CMTPL,  PropertyInsurance,  HealthInsurance
}

public class InsurancePolicy {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private PolicyType type;
    private String policyNumber;
    private PolicyObject policyObject;
    private Calendar beginningDate;
    private Calendar deadline;

    public InsurancePolicy(PolicyType type, String policyNumber, PolicyObject policyObject, GregorianCalendar beginningDate, GregorianCalendar deadline) {
        this.type = type;
        this.policyNumber = policyNumber;
        this.policyObject = policyObject;
        this.beginningDate = beginningDate;
        this.deadline = deadline;
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

    public Calendar getBeginningDate() {
        return beginningDate;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public String getFormattedBeginningDate() {
        return dateFormat.format(beginningDate.getTime());
    }

    public String getFormattedDeadline() {
        return dateFormat.format(deadline.getTime());
    }
}
