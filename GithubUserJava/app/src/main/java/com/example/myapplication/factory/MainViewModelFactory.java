package com.example.myapplication.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

//import com.example.myapplication.data.local.datastore.SettingPreferences;
import com.example.myapplication.viewmodel.MainViewModel;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {


    public MainViewModelFactory() {

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel();
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
