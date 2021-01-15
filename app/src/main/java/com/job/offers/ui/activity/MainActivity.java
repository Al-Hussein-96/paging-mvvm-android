package com.job.offers.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.job.offers.R;
import com.job.offers.ui.fragment.JobOffersListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, JobOffersListFragment.newInstance())
                    .commitNow();
        }
    }
}