package com.example.myapplication.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.local.datastore.SettingPreferences
import com.example.myapplication.viewmodel.SettingViewModel

class SettingViewModelFactory(private val pref: SettingPreferences) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}