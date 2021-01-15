package com.job.offers.data.source.remote;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.ItemKeyedDataSource;
import androidx.paging.PageKeyedDataSource;

import com.job.offers.data.model.JobOffer;
import com.job.offers.data.source.datasource.JobOffersDataSource;
import com.job.offers.data.source.remote.ApiController.JobOffersController;
import com.job.offers.data.source.remote.Response.JobOffersResponse;

import java.util.Date;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobOffersRemoteDateSource extends PageKeyedDataSource<Integer, JobOffer> {
    private static JobOffersRemoteDateSource INSTANCE;
    private JobOffersController jobOffersController;
    private MutableLiveData<NetworkState> networkStateMutableLiveData;
    private MutableLiveData<NetworkState> initialLoadingMutableLiveData;
    private Executor retryExecutor;


    public JobOffersRemoteDateSource(Executor retryExecutor, JobOffersController webService) {
        jobOffersController = webService;
        networkStateMutableLiveData = new MutableLiveData<>();
        initialLoadingMutableLiveData = new MutableLiveData<>();
        this.retryExecutor = retryExecutor;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, JobOffer> callback) {

        jobOffersController = RetrofitClient.buildServices(JobOffersController.class);

        Call<JobOffersResponse> responseCall = jobOffersController.getJobOffers(1);

        initialLoadingMutableLiveData.postValue(NetworkState.LOADING);
        networkStateMutableLiveData.postValue(NetworkState.LOADING);

        responseCall.enqueue(new Callback<JobOffersResponse>() {
            @Override
            public void onResponse(Call<JobOffersResponse> call, Response<JobOffersResponse> response) {

                initialLoadingMutableLiveData.postValue(NetworkState.LOADING);
                networkStateMutableLiveData.postValue(NetworkState.LOADED);

                callback.onResult(response.body().getItems(), null, 2);
            }

            @Override
            public void onFailure(Call<JobOffersResponse> call, Throwable t) {
                networkStateMutableLiveData.postValue(new NetworkState(NetworkState.Status.FAILED, t.getMessage()));
            }
        });


    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, JobOffer> callback) {
        jobOffersController = RetrofitClient.buildServices(JobOffersController.class);
        networkStateMutableLiveData.postValue(NetworkState.LOADING);

        Call<JobOffersResponse> responseCall = jobOffersController.getJobOffers(params.key);

        responseCall.enqueue(new Callback<JobOffersResponse>() {
            @Override
            public void onResponse(Call<JobOffersResponse> call, Response<JobOffersResponse> response) {
                int nextKey = params.key + 1;

                initialLoadingMutableLiveData.postValue(NetworkState.LOADING);
                networkStateMutableLiveData.postValue(NetworkState.LOADED);


                callback.onResult(response.body().getItems(), nextKey);
            }

            @Override
            public void onFailure(Call<JobOffersResponse> call, Throwable t) {
                String errorMessage = t.getMessage();
                networkStateMutableLiveData.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, JobOffer> callback) {

    }

    public MutableLiveData<NetworkState> getNetworkStateMutableLiveData() {
        return networkStateMutableLiveData;
    }

    //    @Override
//    public void getJobOffers(@NonNull LoadJobOffersCallback callback) {
//
//        jobOffersController = RetrofitClient.buildServices(JobOffersController.class);
//
//        Call<JobOffersResponse> responseCall = jobOffersController.getJobOffers();
//
//        responseCall.enqueue(new Callback<JobOffersResponse>() {
//            @Override
//            public void onResponse(Call<JobOffersResponse> call, Response<JobOffersResponse> response) {
//                callback.onJobOffersLoaded(response.body().getItems());
//            }
//
//            @Override
//            public void onFailure(Call<JobOffersResponse> call, Throwable t) {
//
//            }
//        });
//
//    }
}
