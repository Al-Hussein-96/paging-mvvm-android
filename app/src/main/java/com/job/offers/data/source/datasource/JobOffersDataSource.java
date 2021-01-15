package com.job.offers.data.source.datasource;

import androidx.annotation.NonNull;

import com.job.offers.data.model.JobOffer;

import java.util.List;

public interface JobOffersDataSource {

    interface LoadJobOffersCallback {
        void onJobOffersLoaded(List<JobOffer> jobOffers);

        void onDataNotAvailable();
    }
    void getJobOffers(@NonNull LoadJobOffersCallback callback);

}
