package com.example.myapplication.data.local.datastore;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingPreferences {
    private SharedPreferences preferences;

    public SettingPreferences(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void setDarkMode(boolean isDarkMode) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("dark_mode", isDarkMode);
        editor.apply();
    }

    public boolean isDarkMode() {
        return preferences.getBoolean("dark_mode", false);
    }
}
