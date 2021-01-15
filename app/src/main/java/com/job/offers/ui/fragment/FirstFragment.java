package com.job.offers.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.job.offers.R;
import com.job.offers.data.model.JobOffer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import kotlinx.coroutines.Job;

import static com.job.offers.ui.activity.DetailsActivity.JOB_OFFER_EXTRA;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {


    public FirstFragment() {
        // Required empty public constructor
    }


    public static FirstFragment newInstance(JobOffer jobOffer) {
        FirstFragment fragment = new FirstFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(JOB_OFFER_EXTRA, jobOffer);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        view.findViewById(R.id.textView_description).setVisibility(View.GONE);
        view.findViewById(R.id.btn_learn_mode).setVisibility(View.GONE);

        if (getArguments() != null) {
            JobOffer jobOffer = (JobOffer) getArguments().get(JOB_OFFER_EXTRA);

            ((TextView) view.findViewById(R.id.textView_company)).setText(jobOffer.getCompany());

                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault());

                try {
                    Date date = dateFormat2.parse(jobOffer.getCreatedOn());
                    ((TextView) view.findViewById(R.id.textView_date)).setText(dateFormat1.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ((TextView) view.findViewById(R.id.textView_city)).setText(jobOffer.getCity());
                ((TextView) view.findViewById(R.id.textView_jobType)).setText(jobOffer.getJobType());
                ((TextView) view.findViewById(R.id.textView_workingMode)).setText(jobOffer.getWorkingMode());


        }


        return view;
    }
}