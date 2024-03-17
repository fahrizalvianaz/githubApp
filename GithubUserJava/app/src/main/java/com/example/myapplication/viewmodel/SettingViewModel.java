
package com.example.myapplication.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.local.datastore.SettingPreferences;

public class SettingViewModel extends ViewModel {
    private SettingPreferences preferences;

    public SettingViewModel(SettingPreferences preferences) {
        this.preferences = preferences;
    }

    public void setDarkMode(boolean isDarkMode) {
        preferences.setDarkMode(isDarkMode);
    }

    public boolean isDarkMode() {
        return preferences.isDarkMode();
    }
}
