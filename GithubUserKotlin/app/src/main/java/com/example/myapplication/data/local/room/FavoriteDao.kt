package com.example.myapplication.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.data.local.entity.FavoriteEntity

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(fav: FavoriteEntity)

    @Update
    fun update(fav: FavoriteEntity)

    @Delete
    fun delete(fav: FavoriteEntity)

    @Query("SELECT  * from favorite")
    fun getAllFavorite(): LiveData<List<FavoriteEntity>>

    @Query("SELECT  * from favorite WHERE username = :username")
    fun getUserFavoriteByUsername(username: String): LiveData<List<FavoriteEntity>>

}