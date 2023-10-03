package com.example.hamrahchallenge8.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.hamrahchallenge8.R
import com.example.hamrahchallenge8.api.MainAPI
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() , OnMapReadyCallback{

    var weatherStatus: Double? = null
    //var fusedLocationProviderClient = FusedLocationProviderClient
    val REQUEST_CODE = 101


    lateinit var mainAPI: MainAPI
    lateinit var latLng: LatLng
    lateinit var markerOptions: MarkerOptions


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtWeatherStatus = findViewById<TextView>(R.id.txtWeatherStatus)

        weatherStatus = 0.0
        txtWeatherStatus.setText(String.format(getResources().getString(R.string.weather_selected_status),
            DecimalFormat("##.##").format(weatherStatus)));
    }

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }
}