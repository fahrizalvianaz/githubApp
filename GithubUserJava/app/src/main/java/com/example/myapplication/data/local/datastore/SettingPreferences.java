//package com.example.myapplication.data.local.datastore;
//
//import androidx.datastore.core.DataStore;
//import androidx.datastore.preferences.core.Preferences;
//import androidx.datastore.preferences.core.preferencesKey;
//import androidx.datastore.preferences.core.edit;
//import kotlinx.coroutines.flow.Flow;
//import kotlinx.coroutines.flow.map;
//
//public class SettingPreferences {
//    private final DataStore<Preferences> dataStore;
//    private static final preferencesKey<Boolean> THEME_KEY = preferencesKey("theme_setting");
//
//    private SettingPreferences(DataStore<Preferences> dataStore) {
//        this.dataStore = dataStore;
//    }
//
//    public Flow<Boolean> getThemeSetting() {
//        return dataStore.data().map(preferences -> preferences.get(THEME_KEY));
//    }
//
//    public void saveThemeSetting(Boolean isDarkModeActive) {
//        dataStore.edit(preferences -> {
//            preferences.set(THEME_KEY, isDarkModeActive);
//            return null;
//        });
//    }
//
//    private static volatile SettingPreferences INSTANCE;
//
//    public static SettingPreferences getInstance(DataStore<Preferences> dataStore) {
//        if (INSTANCE == null) {
//            synchronized (SettingPreferences.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new SettingPreferences(dataStore);
//                }
//            }
//        }
//        return INSTANCE;
//    }
//}
