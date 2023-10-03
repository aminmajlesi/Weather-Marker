package com.example.hamrahchallenge8.repository

import com.example.hamrahchallenge8.api.RetrofitInstance

class MainRepository {

    suspend fun getWeatherData(lat: Double , lon: Double , apikey: String) =
        RetrofitInstance.api.getWeatherData(lat , lon , apikey)

}