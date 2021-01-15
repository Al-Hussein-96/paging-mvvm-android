package com.job.offers.data.source.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.job.offers.data.model.JobOffer;
import com.job.offers.data.source.remote.ApiController.JobOffersController;
import com.job.offers.data.source.remote.JobOffersRemoteDateSource;

import java.util.concurrent.Executor;

public class JobOffersDataSourceFactory extends DataSource.Factory<Integer, JobOffer> {
    private JobOffersRemoteDateSource dataSource;
    private MutableLiveData<JobOffersRemoteDateSource> mutableLiveData;
    private JobOffersController jobOffersController;
    private Executor executor;

    public JobOffersDataSourceFactory(Executor executor, JobOffersController jobOffersController) {
        this.jobOffersController = jobOffersController;
        this.executor = executor;
        mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource<Integer, JobOffer> create() {
        dataSource = new JobOffersRemoteDateSource(executor,jobOffersController);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }
    public MutableLiveData<JobOffersRemoteDateSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
