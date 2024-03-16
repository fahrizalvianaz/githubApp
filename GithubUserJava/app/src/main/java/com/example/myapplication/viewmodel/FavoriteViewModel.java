package com.example.myapplication.viewmodel;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.myapplication.data.local.entity.FavoriteEntity;
import com.example.myapplication.repository.FavoriteRepository;

import java.util.List;

public class FavoriteViewModel extends ViewModel {

    private final FavoriteRepository mFavRepository;

    public FavoriteViewModel(Application application) {
        mFavRepository = new FavoriteRepository(application);
    }

    public LiveData<List<FavoriteEntity>> getAllFavorites() {
        return mFavRepository.getAllFavorites();
    }
}
