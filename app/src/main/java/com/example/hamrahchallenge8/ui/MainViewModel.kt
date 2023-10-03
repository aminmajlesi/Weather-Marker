package com.example.hamrahchallenge8.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hamrahchallenge8.MainApplication
import com.example.hamrahchallenge8.models.ResponseMain
import com.example.hamrahchallenge8.repository.MainRepository
import com.example.hamrahchallenge8.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MainViewModel(
    app: Application,
    val mainRepository: MainRepository
) : AndroidViewModel(app) {

    val weatherData: MutableLiveData<Resource<ResponseMain>> = MutableLiveData()

    fun getWeather(lat: Double , lon: Double , apikey: String) = viewModelScope.launch {
        getWeatherData(lat , lon , apikey)
    }

    private suspend fun getWeatherData(lat: Double , lon: Double , apikey: String) {
        weatherData.postValue(Resource.Loading())
        try {
            if(hasInternetConnection()) {
                val response = mainRepository.getWeatherData(lat , lon , apikey)
                weatherData.postValue(handleWeatherResponse(response))
            } else {
                weatherData.postValue(Resource.Error("No internet connection"))
            }
        } catch(t: Throwable) {
            when(t) {
                is IOException -> weatherData.postValue(Resource.Error("Network Failure"))
                else -> weatherData.postValue(Resource.Error("Conversion Error"))
            }
        }
    }


    private fun handleWeatherResponse(response: Response<ResponseMain>) : Resource<ResponseMain> {

        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())


    }
    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<MainApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return false
    }

}