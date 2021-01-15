package com.job.offers.data.source.remote.ApiController;

import androidx.paging.DataSource;

import com.job.offers.data.model.JobOffer;
import com.job.offers.data.source.remote.Response.JobOffersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JobOffersController {
    @GET("v1/JobOffers/List")
    Call<JobOffersResponse> getJobOffers(@Query("page") int page);


}
