package hu.ait.android.weatherapp.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

import hu.ait.android.weatherapp.CitySelectInterface;
import hu.ait.android.weatherapp.R;

public class AlertDFragment extends DialogFragment {

    private CitySelectInterface citySelectInterface;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            citySelectInterface = (CitySelectInterface)activity;
        } catch (Exception e)
        {

            throw new ClassCastException("Activity does not implement CitySelectInterface");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final EditText input = new EditText(getActivity());


        return new AlertDialog.Builder(getActivity())
                // Set Dialog Icon
                .setIcon(R.drawable.city)
                        // Set Dialog Title
                .setTitle("Enter a City")
                        // Set Dialog Message
                .setView(input)
                        // Positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String city = input.getText().toString();

                        citySelectInterface.citySelected(city);
                    }
                })

                        // Negative Button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,	int which) {
                        // Do something else
                    }
                }).create();


    }
}