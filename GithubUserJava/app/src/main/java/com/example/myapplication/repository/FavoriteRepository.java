package com.example.myapplication.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.myapplication.data.local.entity.FavoriteEntity;
import com.example.myapplication.data.local.room.FavoriteDao;
import com.example.myapplication.data.local.room.FavoriteRoomDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoriteRepository {

    private final FavoriteDao favDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public FavoriteRepository(Application application) {
        FavoriteRoomDatabase db = FavoriteRoomDatabase.getDatabase(application);
        favDao = db.favDao();
    }

    public LiveData<List<FavoriteEntity>> getAllFavorites() {
        return favDao.getAllFavorite();
    }

    public LiveData<List<FavoriteEntity>> getUserFavoriteByUsername(String username) {
        return favDao.getUserFavoriteByUsername(username);
    }

    public void insert(FavoriteEntity fav) {
        executorService.execute(() -> favDao.insert(fav));
    }

    public void delete(FavoriteEntity fav) {
        executorService.execute(() -> favDao.delete(fav));
    }
}
