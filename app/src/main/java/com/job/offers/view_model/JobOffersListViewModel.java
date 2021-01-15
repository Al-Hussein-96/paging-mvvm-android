package com.job.offers.view_model;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.job.offers.data.model.JobOffer;
import com.job.offers.data.source.datasource.JobOffersDataSourceFactory;
import com.job.offers.data.source.remote.ApiController.JobOffersController;
import com.job.offers.data.source.remote.JobOffersRemoteDateSource;
import com.job.offers.data.source.remote.NetworkState;
import com.job.offers.data.source.remote.RetrofitClient;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class JobOffersListViewModel extends ViewModel {
    private JobOffersController jobOffersController;
    public LiveData<PagedList<JobOffer>> jobOffersPagedListLiveData;
    private LiveData<NetworkState> networkStateLiveData;
    private Executor executor;
    private LiveData<JobOffersRemoteDateSource> dataSource;

    public JobOffersListViewModel() {
        executor = Executors.newFixedThreadPool(5);

        jobOffersController = RetrofitClient.buildServices(JobOffersController.class);
        JobOffersDataSourceFactory factory = new JobOffersDataSourceFactory(executor, jobOffersController);
        dataSource = factory.getMutableLiveData();

        networkStateLiveData = Transformations.switchMap(factory.getMutableLiveData(), new Function<JobOffersRemoteDateSource, LiveData<NetworkState>>() {
            @Override
            public LiveData<NetworkState> apply(JobOffersRemoteDateSource source) {
                return source.getNetworkStateMutableLiveData();
            }
        });

        PagedList.Config pageConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(2)
                .setPageSize(2).build();


        jobOffersPagedListLiveData = new LivePagedListBuilder<Integer, JobOffer>(factory, pageConfig).setFetchExecutor(executor).build();
    }

    public LiveData<PagedList<JobOffer>> getJobOffersPagedListLiveData() {
        return jobOffersPagedListLiveData;
    }

    public LiveData<NetworkState> getNetworkStateLiveData() {
        return networkStateLiveData;
    }
}