package com.job.offers.data.source.repository;

import androidx.annotation.NonNull;

import com.job.offers.data.model.JobOffer;
import com.job.offers.data.source.datasource.JobOffersDataSource;

import java.util.List;

public class JobOffersRepository implements JobOffersDataSource {
    private static JobOffersRepository INSTANCE = null;
    private final JobOffersDataSource mJobOffersDataSource;

    public JobOffersRepository(JobOffersDataSource mJobOffersDataSource) {
        this.mJobOffersDataSource = mJobOffersDataSource;
    }

    public static JobOffersRepository getInstance(JobOffersDataSource gameRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new JobOffersRepository(gameRemoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getJobOffers(@NonNull LoadJobOffersCallback callback) {
        mJobOffersDataSource.getJobOffers(new LoadJobOffersCallback() {
            @Override
            public void onJobOffersLoaded(List<JobOffer> jobOffers) {
                callback.onJobOffersLoaded(jobOffers);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }
}
