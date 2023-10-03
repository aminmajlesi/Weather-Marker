package com.example.hamrahchallenge8.api

import com.example.hamrahchallenge8.models.ResponseMain
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainAPI {

    @GET("/data/2.5/weather?")
    suspend fun getWeatherData(
        @Query("lat") lat : Double ,
        @Query("lon") lon : Double ,
        @Query("appid") appid : String
    ): Response<ResponseMain>

}