package hu.ait.android.weatherapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import hu.ait.android.weatherapp.data.City;
import hu.ait.android.weatherapp.data.HttpAsyncTask;
import hu.ait.android.weatherapp.fragments.AlertDFragment;
import hu.ait.android.weatherapp.fragments.DFragment;
import hu.ait.android.weatherapp.views.CityRecyclerAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CitySelectInterface {

    FragmentManager fm = getSupportFragmentManager();
    private CityRecyclerAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDFragment alert = new AlertDFragment();
                alert.show(fm, "Alert Dialog Fragment");
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        adapter = new CityRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);



    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(brWeatherReceiver,
                new IntentFilter(HttpAsyncTask.FILTER_HTTP_RESULT));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(brWeatherReceiver);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_new_city) {
            // Handle the camera action
            AlertDFragment alert = new AlertDFragment();
            alert.show(fm, "Alert Dialog Fragment");
        } else if (id == R.id.nav_about) {
            DFragment dfrag = new DFragment();
            dfrag.show(fm, "DF");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private BroadcastReceiver brWeatherReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String rawReslt = intent.getStringExtra(
                    HttpAsyncTask.KEY_EXCHANGE_RESULT);
            try{
                City newCity = new City();
                JSONObject jsonObject = new JSONObject(rawReslt);
                String temp = jsonObject.getJSONObject("main").getString("temp");
                String city = jsonObject.getString("name");

                JSONArray jArr = jsonObject.getJSONArray("weather");

                String description = "";
                for (int i=0; i<jArr.length(); i++){
                   JSONObject childJSON = jArr.getJSONObject(i);
                    description = childJSON.getString("description");
                }

                String tempMin = jsonObject.getJSONObject("main").getString("temp_min");
                String tempMax = jsonObject.getJSONObject("main").getString("temp_max");
                String country = jsonObject.getJSONObject("sys").getString("country");
                String humidity = jsonObject.getJSONObject("main").getString("humidity");
                String wind = jsonObject.getJSONObject("wind").getString("speed");

                newCity.setName(city);
                newCity.setCountry(country);
                newCity.setCurrTemp(temp);
                newCity.setMaxTemp(tempMax);
                newCity.setMinTemp(tempMin);
                newCity.setDescription(description);
                newCity.setHumidity(humidity);
                newCity.setWindSpeed(wind);

                adapter.addCity(newCity);
                Toast.makeText(MainActivity.this, city +" added.", Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


    @Override
    public void citySelected(String city) {
        HttpAsyncTask asyncTask = new HttpAsyncTask(getApplicationContext());
        asyncTask.execute("http://api.openweathermap.org/data/2.5/weather?q="+city+"&units=imperial&appid=2de143494c0b295cca9337e1e96b00e0");
    }
}
