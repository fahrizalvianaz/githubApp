package com.example.myapplication.ui;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import com.example.myapplication.R;
import com.example.myapplication.data.local.datastore.SettingPreferences;
import com.example.myapplication.factory.SettingViewModelFactory;
import com.example.myapplication.viewmodel.SettingViewModel;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingActivity extends AppCompatActivity {

    private SettingViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        SettingPreferences preferences = new SettingPreferences(this);
        viewModel = new ViewModelProvider(this, new SettingViewModelFactory(preferences)).get(SettingViewModel.class);

        SwitchMaterial switchTheme = findViewById(R.id.switch_theme);
        switchTheme.setChecked(viewModel.isDarkMode());

        switchTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            viewModel.setDarkMode(isChecked);
            recreate();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (viewModel.isDarkMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
