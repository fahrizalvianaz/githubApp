package com.example.myapplication.factory;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.myapplication.viewmodel.FavoriteViewModel;

public class FavoriteViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile FavoriteViewModelFactory INSTANCE;
    private final Application application;

    private FavoriteViewModelFactory(Application application) {
        this.application = application;
    }

    public static FavoriteViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (FavoriteViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavoriteViewModelFactory(application);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FavoriteViewModel.class)) {
            return (T) new FavoriteViewModel(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
