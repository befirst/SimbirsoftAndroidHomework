package com.no_fate.simbirsoftandroidhomework;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class PolicyAdapterWithRX extends RecyclerView.Adapter<PolicyAdapterWithRX.PolicyViewHolder> {
    private final static int CODE_ERROR = -1;

    private Context parentContext;
    private List<InsurancePolicy> policies;
    private Calendar nowTime;
    private Subject<SubjectItem> observable;

    public PolicyAdapterWithRX(Context context, List<InsurancePolicy> policies) {
        this.parentContext = context;
        this.policies = policies;
        nowTime = Calendar.getInstance(Locale.getDefault());
        observable = PublishSubject.create();
        observable.subscribeOn(Schedulers.newThread())
                .subscribe(item -> handleBindFromSubjectItem(item));
    }

    public static class PolicyViewHolder extends RecyclerView.ViewHolder{
        private CardView cvCustom;
        private ImageView ivIcon;
        private TextView tvMainText;
        private ImageView ivChevron;
        private ProgressBar pbTimeOfAction;
        private TextView tvSecondaryText;
        private ImageView ivCart;

        public PolicyViewHolder(View itemView) {
            super(itemView);
            cvCustom = itemView.findViewById(R.id.customCard);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            tvMainText = itemView.findViewById(R.id.tvMainText);
            ivChevron = itemView.findViewById(R.id.ivChevron);
            pbTimeOfAction = itemView.findViewById(R.id.pbTimeOfAction);
            tvSecondaryText = itemView.findViewById(R.id.tvSecondaryText);
            ivCart = itemView.findViewById(R.id.ivCart);
        }
    }

    @Override
    public PolicyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cardview, parent, false);
        return new PolicyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PolicyViewHolder holder, final int position) {
        observable.onNext(new SubjectItem(holder, position));
    }

    @Override
    public int getItemCount() {
        return policies.size();
    }

    private void handleBindFromSubjectItem(SubjectItem item){
        handleBind(item.getHolder(), item.getPosition());
    }

    private void handleBind(PolicyViewHolder holder, int position){
        final int BUY_NEW_ACCESS = 30;

        final InsurancePolicy policy = policies.get(position);

        if(getIconResourceId(policy) != CODE_ERROR){
            holder.ivIcon.setImageResource(getIconResourceId(policy));
        }

        holder.tvMainText.setText(getFormattedMainText(policy));

        int daysToTheEnd = policy.getDistanceInDays(nowTime);

        holder.tvSecondaryText.setText(getFormattedSecondaryText(policy, daysToTheEnd));

        holder.pbTimeOfAction.setProgress(policy.getProgress(nowTime));
        holder.pbTimeOfAction.setProgressTintList(
                ColorStateList.valueOf(
                        ContextCompat.getColor(
                                parentContext,
                                getProgressColorId(daysToTheEnd)
                        )
                )
        );

        if(daysToTheEnd < BUY_NEW_ACCESS){
            holder.ivCart.setVisibility(View.VISIBLE);
            holder.ivCart.setOnClickListener(listener ->  {
                        policy.buyPolicy(nowTime);
                        notifyItemChanged(position);
                    }
            );
        } else{
            holder.ivCart.setVisibility(View.INVISIBLE);
        }
    }

    private int getIconResourceId(InsurancePolicy policy) {
        switch(policy.getType()){
            case CNCInsurance: return R.drawable.icon_car_kasko;
            case CMTPL: return R.drawable.icon_car;
            case PropertyInsurance: return R.drawable.icon_key;
            case HealthInsurance: return R.drawable.icon_medec;
        }
        return CODE_ERROR;
    }

    private int getTypeTextResourceId(InsurancePolicy policy) {
        switch(policy.getType()){
            case CNCInsurance: return R.string.CNCInsurance;
            case CMTPL: return R.string.CMTPL;
            case PropertyInsurance: return R.string.PropertyInsurance;
            case HealthInsurance: return R.string.HealthInsurance;
        }
        return CODE_ERROR;
    }

    private Spannable getFormattedMainText(InsurancePolicy policy){
        String type;
        if(getTypeTextResourceId(policy) != CODE_ERROR){
            type = parentContext.getResources().getString(getTypeTextResourceId(policy));
        } else{
            type = parentContext.getResources().getString(R.string.error);
        }
        String number = policy.getPolicyNumber();
        String object = policy.getPolicyObject();
        String date = parentContext.getResources().getString(R.string.from_date) + " " +
                    policy.getFormattedBeginningDate() +
                    " " + parentContext.getResources().getString(R.string.to_date) + " " +
                    policy.getFormattedDeadline();

        String result;

        if(policy.getType() == PolicyType.HealthInsurance){
            result = type + System.getProperty("line.separator") +
                    number + System.getProperty("line.separator");
        } else {
            result = type + " " +
                    number + System.getProperty("line.separator");
        }
        result += object + System.getProperty("line.separator") + date;

        Spannable sb = new SpannableString(result);
        //roboto = sans-serif
        sb.setSpan(
                new TypefaceSpan("sans-serif-medium"),
                result.indexOf(type),
                result.indexOf(type)+ type.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        ); //roboto-medium
        sb.setSpan(
                new ForegroundColorSpan(ContextCompat.getColor(parentContext, R.color.blackThree87)),
                result.indexOf(type),
                result.indexOf(type)+ type.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );//color.blackThree87
        sb.setSpan(
                new ForegroundColorSpan(ContextCompat.getColor(parentContext, R.color.black212121)),
                result.indexOf(object),
                result.indexOf(object)+ object.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );//color.black212121
        return sb;
    }

    private String getFormattedSecondaryText(InsurancePolicy policy, int daysToTheEnd){
        String days = String.valueOf(daysToTheEnd);
        String deadline = policy.getFormattedDeadline();
        String result;
        if((daysToTheEnd >= 10) && (daysToTheEnd <= 19)){
            result = parentContext.getResources().getString(R.string.remains_many) + " " +
                    days + " " + parentContext.getResources().getString(R.string.days_many) + " ";
        } else if(daysToTheEnd % 10 > 4){
            result = parentContext.getResources().getString(R.string.remains_many) + " " +
                    days + " " + parentContext.getResources().getString(R.string.days_many) + " ";
        } else if(daysToTheEnd % 10 > 1){
            result = parentContext.getResources().getString(R.string.remains_many) + " " +
                    days + " " + parentContext.getResources().getString(R.string.days_many_2) + " ";
        } else if(daysToTheEnd % 10 > 0){
            result = parentContext.getResources().getString(R.string.remains_one) + " " +
                    days + " " + parentContext.getResources().getString(R.string.day) + " ";
        } else {
            result = parentContext.getResources().getString(R.string.remains_many) + " " +
                    days + " " + parentContext.getResources().getString(R.string.days_many) + " ";
        }
        result += "(" + parentContext.getResources().getString(R.string.to_date_2) + " " +
                deadline + ")";
        return result;
    }

    private int getProgressColorId(int daysToTheEnd){
        final int FIRST_LIMIT = 7;
        final int SECOND_LIMIT = 30;
        final int BAD_COLOR_ID = R.color.coral_two;
        final int MIDDLE_COLOR_ID = R.color.orangeish;
        final int GOOD_COLOR_ID = R.color.colorMintMain;

        if(daysToTheEnd < FIRST_LIMIT){
            return BAD_COLOR_ID;
        } else if(daysToTheEnd < SECOND_LIMIT){
            return MIDDLE_COLOR_ID;
        } else{
            return GOOD_COLOR_ID;
        }
    }

    class SubjectItem{
        private PolicyViewHolder holder;
        private int position;

        public SubjectItem(PolicyViewHolder holder, int position) {
            this.holder = holder;
            this.position = position;
        }

        public PolicyViewHolder getHolder() {
            return holder;
        }

        public int getPosition() {
            return position;
        }

    }
}
