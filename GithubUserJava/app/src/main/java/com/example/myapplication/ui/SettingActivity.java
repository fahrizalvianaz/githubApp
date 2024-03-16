package com.example.myapplication.ui;

import static androidx.datastore.preferences.PreferenceDataStoreDelegateKt.preferencesDataStore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;

//import com.example.myapplication.data.local.datastore.SettingPreferences;
import com.example.myapplication.databinding.ActivitySettingBinding;
//import com.example.myapplication.factory.SettingViewModelFactory;
//import com.example.myapplication.viewmodel.SettingViewModel;

public class SettingActivity extends AppCompatActivity {


    private ActivitySettingBinding binding;
//    private final DataStore<Preferences> dataStore = preferencesDataStore(name = "settings");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
//
//        SettingPreferences pref = SettingPreferences.getInstance(dataStore);
//        SettingViewModel viewModel = new ViewModelProvider(this, new SettingViewModelFactory(pref)).get(SettingViewModel.class);
//
//        viewModel.getThemeSettings().observe(this, isDarkModeActive -> {
//            if (isDarkModeActive) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                binding.switchTheme.setChecked(true);
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                binding.switchTheme.setChecked(false);
//            }
//        });

        binding.switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                binding.switchTheme.setChecked(isChecked);
//                viewModel.saveThemeSetting(isChecked);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
