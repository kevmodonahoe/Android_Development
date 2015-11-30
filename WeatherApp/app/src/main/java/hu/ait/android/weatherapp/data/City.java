package hu.ait.android.weatherapp.data;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by kdonahoe on 11/26/15.
 */


public class City extends SugarRecord<City> implements Serializable {

    String name;
    String country;
    String currTemp;
    String maxTemp;
    String minTemp;
    String description;
    String humidity;
    String windSpeed;

    public City(){}
    public City(String name, String country, String currTemp, String maxTemp, String minTemp, String description,
                String humidity, String windSpeed) {

        this.name = name;
        this.country = country;
        this.currTemp = currTemp;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.description = description;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrTemp() {
        return currTemp;
    }

    public void setCurrTemp(String currTemp) {
        this.currTemp = currTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

}
