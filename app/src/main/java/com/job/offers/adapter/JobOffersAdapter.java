package com.job.offers.adapter;

import android.content.Context;
import android.content.Intent;
import android.icu.util.LocaleData;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.job.offers.R;
import com.job.offers.data.model.JobOffer;
import com.job.offers.data.source.remote.NetworkState;
import com.job.offers.ui.activity.DetailsActivity;
import com.job.offers.view_model.JobOffersListViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.job.offers.ui.activity.DetailsActivity.JOB_OFFER_EXTRA;

public class JobOffersAdapter extends PagedListAdapter<JobOffer, JobOffersAdapter.JobOfferViewHolder> {
    private Context context;
    private NetworkState mNetworkState;


    public JobOffersAdapter(Context context) {
        super(JobOffer.DIFF_CALL);
        this.context = context;
    }

    @NonNull
    @Override
    public JobOfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_offer, parent, false);
        JobOfferViewHolder rcv = new JobOfferViewHolder(layoutView);
        return rcv;
    }


    @Override
    public void onBindViewHolder(@NonNull JobOfferViewHolder holder, int position) {
        Log.i("Mohammad", "onBindViewHolder: " + getItem(position).getCity());
        holder.textView_company.setText(getItem(position).getCompany());

        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault());

        try {
            Date date = dateFormat2.parse(getItem(position).getCreatedOn());
            holder.textView_date.setText(dateFormat1.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.textView_description.setText(getItem(position).getDescription());
        holder.textView_city.setText(getItem(position).getCity());
        holder.textView_jobType.setText(getItem(position).getJobType());
        holder.textView_workingMode.setText(getItem(position).getWorkingMode());


        holder.btn_learn_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(JOB_OFFER_EXTRA, getItem(position));
                context.startActivity(intent);
            }
        });

    }

    public void setNetworkState(NetworkState networkState) {
        NetworkState prevState = networkState;
        boolean wasLoading = isLoadingData();
        mNetworkState = networkState;
        boolean willLoad = isLoadingData();
        if (wasLoading != willLoad) {
            if (wasLoading) notifyItemRemoved(getItemCount());
            else notifyItemInserted(getItemCount());
        }
    }

    public boolean isLoadingData() {
        return (mNetworkState != null && mNetworkState != NetworkState.LOADED);
    }


    static class JobOfferViewHolder extends RecyclerView.ViewHolder {
        private final View view;
        private final TextView textView_company;
        private final TextView textView_date;
        private final TextView textView_description;
        private final TextView textView_city;
        private final TextView textView_jobType;
        private final TextView textView_workingMode;
        private final Button btn_learn_mode;

        public JobOfferViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            textView_company = itemView.findViewById(R.id.textView_company);
            textView_date = itemView.findViewById(R.id.textView_date);
            textView_description = itemView.findViewById(R.id.textView_description);
            textView_city = itemView.findViewById(R.id.textView_city);
            textView_jobType = itemView.findViewById(R.id.textView_jobType);
            textView_workingMode = itemView.findViewById(R.id.textView_workingMode);
            btn_learn_mode = itemView.findViewById(R.id.btn_learn_mode);
        }
    }
}
