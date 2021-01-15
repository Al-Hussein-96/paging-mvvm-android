package com.job.offers.ui.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.job.offers.R;
import com.job.offers.data.model.JobOffer;

import static com.job.offers.ui.activity.DetailsActivity.JOB_OFFER_EXTRA;

public class MapsFragment extends Fragment implements GoogleMap.OnMarkerClickListener {
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String DESCRIPTION = "description";

    private GoogleMap mMap;


    private final OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            Log.i("MapMohammad", "onMapReady");
            mMap.getUiSettings().setAllGesturesEnabled(false);

            if (getArguments() != null) {
                JobOffer jobOffer = (JobOffer) getArguments().get(JOB_OFFER_EXTRA);

                LatLng sydney = new LatLng(jobOffer.getAddressLatitude(), jobOffer.getAddressLongitude());

                MarkerOptions markerOptions = new MarkerOptions().position(sydney).title(jobOffer.getCompany());
                googleMap.addMarker(markerOptions);

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 8f));
            }


        }
    };

    public static Fragment newInstance(JobOffer jobOffer) {
        MapsFragment mapsFragment = new MapsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(JOB_OFFER_EXTRA, jobOffer);
        mapsFragment.setArguments(bundle);
        return mapsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i("MapMohammad", "onCreateView");

        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        if (getArguments() != null) {
            JobOffer jobOffer = (JobOffer) getArguments().get(JOB_OFFER_EXTRA);
            ((TextView) view.findViewById(R.id.textView_address_description)).setText(jobOffer.getAddressDescription());
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            Log.i("MapMohammad", "onViewCreated");

            mapFragment.getMapAsync(callback);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        return true;
    }
}