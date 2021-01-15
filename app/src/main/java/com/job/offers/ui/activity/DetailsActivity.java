package com.job.offers.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.job.offers.R;
import com.job.offers.data.model.JobOffer;
import com.job.offers.ui.fragment.FirstFragment;
import com.job.offers.ui.fragment.MapsFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import kotlinx.coroutines.Job;

public class DetailsActivity extends AppCompatActivity {
    public static final String JOB_OFFER_EXTRA = "job_offer_extra";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        JobOffer jobOffer = (JobOffer) getIntent().getSerializableExtra(JOB_OFFER_EXTRA);

        ViewPager viewPager = findViewById(R.id.viewPager);

//        SlidePagerAdapter adapter = new SlidePagerAdapter(getSupportFragmentManager());
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), jobOffer);

        viewPager.setAdapter(adapter);

        ((TextView) findViewById(R.id.textView_description)).setText(jobOffer.getDescription());


    }


    private static class MyPagerAdapter extends FragmentStatePagerAdapter {
        private final JobOffer jobOffer;

        public MyPagerAdapter(@NonNull FragmentManager fm, JobOffer jobOffer) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            this.jobOffer = jobOffer;
        }


        @Override
        public int getCount() {
            return 2;
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return FirstFragment.newInstance(jobOffer);
            } else {
                return MapsFragment.newInstance(jobOffer);
            }
        }

    }
}