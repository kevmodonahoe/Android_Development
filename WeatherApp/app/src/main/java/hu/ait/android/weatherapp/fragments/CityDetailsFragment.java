package hu.ait.android.weatherapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hu.ait.android.weatherapp.R;
import hu.ait.android.weatherapp.ViewPagerActivity;

public class CityDetailsFragment extends Fragment {

    Intent getIntent;

    //MAY BE A PROBLEM TO HAVE 2 CONSTRUCTORS
    public CityDetailsFragment(){}

    public CityDetailsFragment(Intent cityIntent) {
        getIntent = cityIntent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.city_details_fragment, container, false);


        final TextView city = (TextView) rootView.findViewById(R.id.cityName);
        final TextView country = (TextView) rootView.findViewById(R.id.countryName);
        final TextView currTemp = (TextView) rootView.findViewById(R.id.currTemp);
        final TextView maxTemp = (TextView) rootView.findViewById(R.id.maxTemp);
        final TextView minTemp = (TextView) rootView.findViewById(R.id.minTemp);
        final TextView description = (TextView) rootView.findViewById(R.id.description);
        final TextView humidity = (TextView) rootView.findViewById(R.id.humidity);
        final TextView wind = (TextView) rootView.findViewById(R.id.wind);


        city.setText("City: " + getIntent.getStringExtra("name"));
        country.setText("Country: " + getIntent.getStringExtra("country"));
        currTemp.setText("Current Temp: " + getIntent.getStringExtra("currTemp"));
        maxTemp.setText("Max Temp: " + getIntent.getStringExtra("maxTemp"));
        minTemp.setText("Min Temp: " + getIntent.getStringExtra("minTemp"));
        description.setText("Description: " + getIntent.getStringExtra("description"));
        humidity.setText("Humidity: "+ getIntent.getStringExtra("humidity"));
        wind.setText("Wind speed: " + getIntent.getStringExtra("wind") + " MPH");


        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((ViewPagerActivity)getActivity()).onFabPressed();

            }
        });

        return rootView;
    }






}
