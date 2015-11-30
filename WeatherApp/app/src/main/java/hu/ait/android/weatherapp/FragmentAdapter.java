package hu.ait.android.weatherapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hu.ait.android.weatherapp.fragments.CityDetailsFragment;
import hu.ait.android.weatherapp.fragments.MyMapFragment;

/**
 * Created by kdonahoe on 11/28/15.
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    Intent cityIntent;


    public FragmentAdapter(FragmentManager fm, Intent intent) {
        super(fm);
        cityIntent = intent;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new CityDetailsFragment(cityIntent);
            case 1:
                return new MyMapFragment();

            default:
                return new CityDetailsFragment(cityIntent);
        }


    }

    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "City Details";
            case 1:
                return "CITY D";
            default:
                return "City Details";
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
