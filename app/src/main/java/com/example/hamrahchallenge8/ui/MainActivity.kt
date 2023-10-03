package com.example.hamrahchallenge8.ui


import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hamrahchallenge8.R
import com.example.hamrahchallenge8.api.MainAPI
import com.example.hamrahchallenge8.repository.MainRepository
import com.example.hamrahchallenge8.util.Resource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() , OnMapReadyCallback{

    var weatherStatus: Double? = null
    var fusedLocationProviderClient: FusedLocationProviderClient? = null
    val REQUEST_CODE = 101

    lateinit var viewModel: MainViewModel

    lateinit var latLng: LatLng
    lateinit var markerOptions: MarkerOptions


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtWeatherStatus = findViewById<TextView>(R.id.txtWeatherStatus)

        weatherStatus = 0.0
        txtWeatherStatus.setText(String.format(getResources().getString(R.string.weather_selected_status),
            DecimalFormat("##.##").format(weatherStatus)));


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        //fetchLocation()

        txtWeatherStatus.setOnClickListener { view ->
            txtWeatherStatus.setText(
                String.format(
                    resources.getString(R.string.weather_selected_status),
                    DecimalFormat("##.##").format(weatherStatus)
                )
            )
        }



        val mainRepository = MainRepository()
        val viewModelProviderFactory = MainViewModelProviderFactory(application, mainRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)


    }

    override fun onMapReady(p0: GoogleMap) {

        p0.setOnMapClickListener { LatLonPoint: LatLng ->
            latLng = LatLonPoint
            markerOptions = MarkerOptions()
            // Set position of marker
            markerOptions.position(latLng)
            // Set title of marker
            markerOptions.title(latLng.latitude.toString() + " : " + latLng.longitude)

            weather()
            getWeather(latLng.latitude , latLng.longitude , resources.getString(R.string.open_weather_maps_app_id))

            // Remove all marker
            p0.clear()
            // Animating to zoom the marker
            p0.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
            // Add marker on map
            p0.addMarker(markerOptions)
        }
    }

    private fun getWeather(lat: Double , lon: Double , apikey: String){
        viewModel.getWeather(lat , lon , apikey)
    }

    private fun weather() {
        viewModel.weatherData.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {

                    response.data?.let { mainResponse ->
                        weatherStatus =
                            mainResponse.main.temp  //- 273.15 //kelvin to celsius
                    }
                }
                is Resource.Error -> {

                    response.message?.let { message ->
                        Toast.makeText(this, "An error occured: $message", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {

                }
            }
        })
    }
}