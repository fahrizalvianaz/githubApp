//
//package com.example.myapplication.viewmodel;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//import com.example.myapplication.data.local.datastore.SettingPreferences;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class SettingViewModel extends ViewModel {
//    private final SettingPreferences pref;
//    private final MutableLiveData<Boolean> themeSettings = new MutableLiveData<>();
//    private final ExecutorService executor = Executors.newSingleThreadExecutor();
//
//    public SettingViewModel(SettingPreferences pref) {
//        this.pref = pref;
//        loadThemeSettings();
//    }
//
//    public LiveData<Boolean> getThemeSettings() {
//        return themeSettings;
//    }
//
//    public void saveThemeSetting(boolean isDarkModeActive) {
//        executor.execute(() -> pref.saveThemeSetting(isDarkModeActive));
//    }
//
//    private void loadThemeSettings() {
//        executor.execute(() -> {
//            try {
//                themeSettings.postValue(pref.getThemeSetting());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    @Override
//    protected void onCleared() {
//        super.onCleared();
//        executor.shutdown();
//    }
//}
