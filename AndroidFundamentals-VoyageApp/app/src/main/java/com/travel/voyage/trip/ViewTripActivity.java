package com.travel.voyage.trip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.travel.voyage.DataManager;
import com.travel.voyage.R;
import com.travel.voyage.retrofit_and_weather.RetrofitInstance;
import com.travel.voyage.retrofit_and_weather.Weather;
import com.travel.voyage.retrofit_and_weather.WeatherApi;
import com.travel.voyage.room.Trip;
import com.travel.voyage.room.TripDao;
import com.travel.voyage.room.TripDataBase;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.INTERNET;

/**
 * @author Beti
 */
public class ViewTripActivity extends AppCompatActivity {

    private static final String API_TOKEN = "e1980f218fc410cc362331590255d98c";
    private static final int INTERNET_PERMISSION = 1;

    private TripDao tripDao;
    private Trip trip;
    private ImageButton favoriteButton;

    private TextView tripName;
    private TextView tripDestination;
    private TextView tripPrice;
    private TextView tripType;
    private TextView tripStartDate;
    private TextView tripEndDate;
    private TextView tripRating;

    private TextView weatherNow;
    private TextView weatherMin;
    private TextView weatherMax;
    private TextView weatherWind;
    private TextView weatherCloud;
    private TextView weatherHumidity;
    private TextView weatherTitle;

    private ImageView weatherIcon;
    private ImageView tripPicture;
    private ConstraintLayout weatherLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_trip);

        TripDataBase tripDataBase = Room.databaseBuilder(this, TripDataBase.class, DataManager.TRIPS_DB_NAME).allowMainThreadQueries().build();
        tripDao = tripDataBase.getTripDao();

        Bundle extras = getIntent().getExtras();
        long tripId = extras.getLong(DataManager.VIEW_TRIP_ID);
        trip = tripDao.getTrip(tripId);

        List<String> tripTypes = Arrays.asList(getString(R.string.city_break), getString(R.string.sea_side), getString(R.string.mountains));

        initializeComponents(tripTypes);
        checkForInternetPermission();

        switch (trip.getType()) {
            case 0:
                tripPicture.setImageResource(R.drawable.citysunset);
                break;
            case 1:
                tripPicture.setImageResource(R.drawable.seasunset);
                break;
            case 2:
                tripPicture.setImageResource(R.drawable.munti);
                break;
            default:
                tripPicture.setImageResource(R.drawable.munte);
        }

        if (trip.isFavorite()) favoriteButton.setImageResource(R.drawable.ic_baseline_star_24);

        WeatherApi service = RetrofitInstance.getRetrofitInstance().create(WeatherApi.class);
        Call<Weather> call = service.getWeather(trip.getDestination(), API_TOKEN, "metric");
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.code() == 200) {
                    Weather main = response.body();

                    if (main.getCurrentTemperature() > 0) {
                        weatherIcon.setImageResource(R.drawable.ic_outline_wb_sunny_24);
                        weatherIcon.setColorFilter(ActivityCompat.getColor(ViewTripActivity.this, R.color.yellow_500));
                    } else {
                        weatherIcon.setImageResource(R.drawable.ic_outline_ice_skating_24);
                        weatherIcon.setColorFilter(ActivityCompat.getColor(ViewTripActivity.this, R.color.blue_500));
                    }

                    weatherNow.setText(String.format("%s%s", main.getCurrentTemperature(), getString(R.string.degree_symbol)));
                    weatherMin.setText(String.format("%s%s", main.getMinTemperature(), getString(R.string.degree_symbol)));
                    weatherMax.setText(String.format("%s%s", main.getMaxTemperature(), getString(R.string.degree_symbol)));
                    weatherWind.setText(String.format("%s %s", main.getWind(), getString(R.string.wind_unit)));
                    weatherCloud.setText(String.format("%s%s", main.getClouds(), getString(R.string.cloud_unit)));
                    weatherHumidity.setText(String.format("%s%s", main.getHumidity(), getString(R.string.humidity_unit)));
                    weatherTitle.setText(R.string.the_weather_right_now);
                    weatherLayout.setVisibility(View.VISIBLE);
                } else {
                    weatherTitle.setText(R.string.weather_error);
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                weatherTitle.setText(R.string.weather_error);
            }

        });
    }

    private void checkForInternetPermission() {
        if (ContextCompat.checkSelfPermission(ViewTripActivity.this, INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ViewTripActivity.this, new String[]{INTERNET}, INTERNET_PERMISSION);
            Toast.makeText(ViewTripActivity.this, R.string.no_permission, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == INTERNET_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ViewTripActivity.this, new String[]{INTERNET}, INTERNET_PERMISSION);
                Toast.makeText(ViewTripActivity.this, R.string.no_permission, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void initializeComponents(List<String> tripTypes) {
        tripName        = findViewById(R.id.trip_name);
        tripDestination = findViewById(R.id.trip_destination);
        tripPrice       = findViewById(R.id.trip_price);
        tripType        = findViewById(R.id.trip_type);
        tripStartDate   = findViewById(R.id.trip_start_date);
        tripEndDate     = findViewById(R.id.trip_end_date);
        tripRating      = findViewById(R.id.trip_rating);

        tripPicture = findViewById(R.id.trip_picture);

        tripName.setText(trip.getName());
        tripDestination.setText(trip.getDestination());
        tripPrice.setText(String.valueOf(trip.getPrice()));
        tripType.setText(tripTypes.get(trip.getType()));
        tripStartDate.setText(trip.getStartDate());
        tripEndDate.setText(trip.getEndDate());
        tripRating.setText(String.valueOf(trip.getRating()));

        weatherNow      = findViewById(R.id.weather_now_value);
        weatherMin      = findViewById(R.id.weather_min_value);
        weatherMax      = findViewById(R.id.weather_max_value);
        weatherWind     = findViewById(R.id.weather_wind_value);
        weatherCloud    = findViewById(R.id.weather_clouds_value);
        weatherHumidity = findViewById(R.id.weather_humidity_value);
        weatherIcon     = findViewById(R.id.weather_icon);
        weatherLayout   = findViewById(R.id.weather_layout);
        weatherTitle    = findViewById(R.id.weather_title);

        favoriteButton = findViewById(R.id.button_favorite);


    }

}