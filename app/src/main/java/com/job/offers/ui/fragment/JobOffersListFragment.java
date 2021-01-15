package com.job.offers.ui.fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.job.offers.R;
import com.job.offers.adapter.JobOffersAdapter;
import com.job.offers.data.model.JobOffer;
import com.job.offers.data.source.remote.NetworkState;
import com.job.offers.view_model.JobOffersListViewModel;

import kotlinx.coroutines.Job;

import static com.job.offers.data.source.remote.NetworkState.LOADING;
import static com.job.offers.data.source.remote.NetworkState.Status.FAILED;
import static com.job.offers.data.source.remote.NetworkState.Status.RUNNING;

public class JobOffersListFragment extends Fragment {

    private JobOffersListViewModel mViewModel;
    private RecyclerView recyclerView;
    private JobOffersAdapter jobOffersAdapter;

    private ShimmerFrameLayout shimmer_view_container;


    public static JobOffersListFragment newInstance() {
        return new JobOffersListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.job_offers_fragment, container, false);

        shimmer_view_container = view.findViewById(R.id.shimmer_view_container);
        recyclerView = view.findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        layoutManager.setReverseLayout(false);
//        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(JobOffersListViewModel.class);
        // TODO: Use the ViewModel

        setupLoadingIndicator();

        jobOffersAdapter = new JobOffersAdapter(getContext());

        mViewModel.getJobOffersPagedListLiveData().observe(getViewLifecycleOwner(), jobOffers -> {
            Log.i("Mohammad", "onChanged: " + jobOffers.size());
            jobOffersAdapter.submitList(jobOffers);
        });

        mViewModel.getNetworkStateLiveData().observe(getViewLifecycleOwner(), networkState -> jobOffersAdapter.setNetworkState(networkState));

        recyclerView.setAdapter(jobOffersAdapter);

    }

    public void setupLoadingIndicator() {

        mViewModel.getNetworkStateLiveData().observe(getViewLifecycleOwner(), networkState -> {
            Log.i("setupLoadingIndicator", "network state: " + networkState.getStatus());
            if (networkState.getStatus() == RUNNING || networkState.getStatus() == FAILED) {
                shimmer_view_container.setVisibility(View.VISIBLE);
                shimmer_view_container.startShimmer();
            } else {
                shimmer_view_container.stopShimmer();
                shimmer_view_container.setVisibility(View.GONE);
            }
        });

//        if (active) {
//            shimmer_view_container.setVisibility(View.VISIBLE);
//            shimmer_view_container.startShimmer();
//
//        } else {
//            shimmer_view_container.stopShimmer();
//            shimmer_view_container.setVisibility(View.GONE);
//        }
    }

}