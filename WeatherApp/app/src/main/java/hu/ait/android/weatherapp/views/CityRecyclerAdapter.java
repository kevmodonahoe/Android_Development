package hu.ait.android.weatherapp.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hu.ait.android.weatherapp.R;
import hu.ait.android.weatherapp.ViewPagerActivity;
import hu.ait.android.weatherapp.data.City;

/**
 * Created by kdonahoe on 11/26/15.
 */
public class CityRecyclerAdapter extends RecyclerView.Adapter<CityRecyclerAdapter.ViewHolder>{

    private List<City> cities;
    private Context context;

    public CityRecyclerAdapter(final Context context) {
        this.context = context;
        cities = City.listAll(City.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cityRow = LayoutInflater.from(context).inflate(R.layout.city_row, parent, false);
        return new ViewHolder(cityRow);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final City city = cities.get(position);
        holder.cityName.setText(city.getName());
        holder.countryName.setText(city.getCountry());
        holder.temp.setText(city.getCurrTemp());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeCity(position);
            }
        });

        holder.viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewPagerActivity.class);

                intent.putExtra("name", city.getName());
                intent.putExtra("country", city.getCountry());
                intent.putExtra("currTemp", city.getCurrTemp());
                intent.putExtra("maxTemp", city.getMaxTemp());
                intent.putExtra("minTemp", city.getMinTemp());
                intent.putExtra("humidity", city.getHumidity());
                intent.putExtra("wind", city.getWindSpeed());
                Log.d("the windspeed is: ", city.getWindSpeed());
                intent.putExtra("description", city.getDescription());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView cityName = null;
        private TextView countryName = null;
        private Button delete = null;
        private Button viewDetails = null;
        private TextView temp = null;


        public ViewHolder(View itemView) {
            super(itemView);
            cityName = (TextView) itemView.findViewById(R.id.name);
            countryName = (TextView) itemView.findViewById(R.id.country);
            delete = (Button) itemView.findViewById(R.id.deleteItem);
            viewDetails = (Button) itemView.findViewById(R.id.viewItem);
            temp = (TextView) itemView.findViewById(R.id.temp);
        }
    }

    public void addCity(City city){
        city.save();
        cities.add(city);
        notifyDataSetChanged();
    }

    public void removeCity(int index){
        if(index > cities.size() - 1){
            String name = cities.get(cities.size()-1).getName();
            cities.get(cities.size() - 1).delete();
            cities.remove(cities.size() - 1);
            notifyItemRemoved(cities.size() - 1);
            Toast.makeText(context, name+" has been removed.", Toast.LENGTH_SHORT).show();
        }
        else{
            String name = cities.get(index).getName();
            cities.get(index).delete();
            cities.remove(index);
            notifyItemRemoved(index);
            Toast.makeText(context, name+" has been removed.", Toast.LENGTH_SHORT).show();
        }

    }
}
