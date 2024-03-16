package com.example.myapplication.data.local.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.data.local.entity.FavoriteEntity;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(FavoriteEntity fav);

    @Update
    void update(FavoriteEntity fav);

    @Delete
    void delete(FavoriteEntity fav);

    @Query("SELECT * FROM favorite")
    LiveData<List<FavoriteEntity>> getAllFavorite();

    @Query("SELECT * FROM favorite WHERE username = :username")
    LiveData<List<FavoriteEntity>> getUserFavoriteByUsername(String username);
}
