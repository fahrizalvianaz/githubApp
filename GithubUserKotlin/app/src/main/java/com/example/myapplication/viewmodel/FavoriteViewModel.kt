package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.local.entity.FavoriteEntity
import com.example.myapplication.repository.FavoriteRepository

class FavoriteViewModel(application : Application) : ViewModel() {
    private val mFavRepository : FavoriteRepository = FavoriteRepository(application)
    fun getAllFavorites() : LiveData<List<FavoriteEntity>> = mFavRepository.getAllFavorites()
}