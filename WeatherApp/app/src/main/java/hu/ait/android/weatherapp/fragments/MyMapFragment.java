package hu.ait.android.weatherapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import hu.ait.android.weatherapp.R;

/**
 * Created by kdonahoe on 11/28/15.
 */
public class MyMapFragment extends SupportMapFragment {

    private GoogleMap myMap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

//        myMap = this.getMapAsync(getActivity());

        return inflater.inflate(R.layout.map_fragment, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
