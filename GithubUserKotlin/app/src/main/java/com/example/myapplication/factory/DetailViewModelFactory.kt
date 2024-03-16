package com.example.myapplication.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.viewmodel.DetailViewModel

class DetailViewModelFactory(private val username: String, private val app: Application) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(username,app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}