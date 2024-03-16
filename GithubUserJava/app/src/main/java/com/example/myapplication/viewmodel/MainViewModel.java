package com.example.myapplication.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//import com.example.myapplication.data.local.datastore.SettingPreferences;
import com.example.myapplication.data.remote.retrofit.ApiConfig;
import com.example.myapplication.data.remote.response.ItemsItem;
import com.example.myapplication.data.remote.response.ResponseGithub;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<ItemsItem>> _user = new MutableLiveData<>();
    public final LiveData<List<ItemsItem>> user = _user;

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public final LiveData<Boolean> isLoading = _isLoading;

    private final MutableLiveData<String> _username = new MutableLiveData<>();
    public final LiveData<String> username = _username;

    private final MutableLiveData<String> _avatarUrl = new MutableLiveData<>();
    public final LiveData<String> avatarUrl = _avatarUrl;

//    private final SettingPreferences pref;
//
//    public MainViewModel(SettingPreferences pref) {
//        this.pref = pref;
//    }

    public void findUser(String username) {
        getUser(username);
    }

    private void getUser(String username) {
        _isLoading.setValue(true);
        Call<ResponseGithub> client = ApiConfig.getApiService().getUser(username);
        client.enqueue(new Callback<ResponseGithub>() {
            @Override
            public void onResponse(Call<ResponseGithub> call, Response<ResponseGithub> response) {
                _isLoading.setValue(false);
                if (response.isSuccessful()) {
                    if (response.isSuccessful()) {
                        _user.setValue(response.body().getItems());
                    }
                } else {
                    Log.e("MainViewModel", "onFailure: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseGithub> call, Throwable t) {
                _isLoading.setValue(false);
                Log.e("MainViewModel", "onFailure: " + t.getMessage());
            }
        });
    }
//    public LiveData<Boolean> getThemeSettings() {
//        return pref.getThemeSetting().asLiveData;
//    }
}
