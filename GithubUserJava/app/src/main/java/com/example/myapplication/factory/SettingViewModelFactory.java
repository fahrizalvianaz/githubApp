package com.example.myapplication.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.myapplication.data.local.datastore.SettingPreferences;
import com.example.myapplication.viewmodel.SettingViewModel;

public class SettingViewModelFactory implements ViewModelProvider.Factory {

    private final SettingPreferences pref;

    public SettingViewModelFactory(SettingPreferences pref) {
        this.pref = pref;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SettingViewModel.class)) {
            return (T) new SettingViewModel(pref);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
