package com.no_fate.simbirsoftandroidhomework;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Denis on 16.08.2017.
 */

public class PolicyAdapter extends RecyclerView.Adapter<PolicyAdapter.PolicyViewHolder> {

    private List<InsurancePolicy> policies;
    private Date nowTime;

    public PolicyAdapter(List<InsurancePolicy> policies) {
        this.policies = policies;
        nowTime = new Date();
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
            cvCustom = (CardView) itemView.findViewById(R.id.customCard);
            ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
            tvMainText = (TextView) itemView.findViewById(R.id.tvMainText);
            ivChevron = (ImageView) itemView.findViewById(R.id.ivChevron);
            pbTimeOfAction = (ProgressBar) itemView.findViewById(R.id.pbTimeOfAction);
            tvSecondaryText = (TextView) itemView.findViewById(R.id.tvSecondaryText);
            ivCart = (ImageView) itemView.findViewById(R.id.ivCart);
        }
    }

    @Override
    public PolicyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cardview, parent, false);
        PolicyViewHolder policyViewHolder = new PolicyViewHolder(view);
        return policyViewHolder;
    }

    @Override
    public void onBindViewHolder(PolicyViewHolder holder, int position) {
        //holder.ivIcon.setImageResource(R.drawable.icon_key);
        holder.tvMainText.setText(
                policies.get(position).getPolicyNumber() + System.getProperty("line.separator") +
                policies.get(position).getPolicyObject() + System.getProperty("line.separator") +
                policies.get(position).getFormattedBeginningDate() + " - " +
                policies.get(position).getFormattedDeadline()
        );
        holder.pbTimeOfAction.setMax((int)(policies.get(position).getDeadline().getTimeInMillis() - policies.get(position).getBeginningDate().getTimeInMillis()));
        Calendar temp = new GregorianCalendar();
        temp.setTime(nowTime);
        holder.pbTimeOfAction.setProgress((int)(temp.getTimeInMillis() - policies.get(position).getBeginningDate().getTimeInMillis()));
    }

    @Override
    public int getItemCount() {
        return policies.size();
    }

}
