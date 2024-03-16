package com.example.myapplication.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.viewmodel.DetailViewModel;

public class DetailViewModelFactory implements ViewModelProvider.Factory {

    private final String username;
    private final Application app;

    public DetailViewModelFactory(String username, Application app) {
        this.username = username;
        this.app = app;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            return (T) new DetailViewModel(username, app);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
