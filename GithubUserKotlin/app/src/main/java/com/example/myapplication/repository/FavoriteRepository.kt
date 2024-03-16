package com.example.myapplication.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.myapplication.data.local.entity.FavoriteEntity
import com.example.myapplication.data.local.room.FavoriteDao
import com.example.myapplication.data.local.room.FavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val favDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        favDao = db.favDao()
    }
    fun getAllFavorites(): LiveData<List<FavoriteEntity>> = favDao.getAllFavorite()
    fun getUserFavoriteByUsername(username: String): LiveData<List<FavoriteEntity>> =
        favDao.getUserFavoriteByUsername(username)
    fun insert(fav: FavoriteEntity) {
        executorService.execute { favDao.insert(fav) }
    }
    fun delete(fav: FavoriteEntity) {
        executorService.execute { favDao.delete(fav) }
    }
}