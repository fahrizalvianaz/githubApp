package com.example.myapplication.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.myapplication.viewmodel.FollowViewModel;

public class FollowViewModelFactory implements ViewModelProvider.Factory {

    private final String username;

    public FollowViewModelFactory(String username) {
        this.username = username;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FollowViewModel.class)) {
            return (T) new FollowViewModel(username);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
