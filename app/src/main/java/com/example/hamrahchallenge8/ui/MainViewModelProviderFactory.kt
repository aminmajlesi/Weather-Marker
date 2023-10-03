package com.example.hamrahchallenge8.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hamrahchallenge8.repository.MainRepository

class MainViewModelProviderFactory (
    val app: Application,
    val mainRepository: MainRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(app, mainRepository) as T
    }
}