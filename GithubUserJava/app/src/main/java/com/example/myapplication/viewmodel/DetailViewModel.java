package com.example.myapplication.viewmodel;

import android.app.Application;
import android.content.ContentValues;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.myapplication.data.local.entity.FavoriteEntity;
import com.example.myapplication.data.remote.response.ResponseGithubDetail;
import com.example.myapplication.data.remote.retrofit.ApiConfig;
import com.example.myapplication.repository.FavoriteRepository;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends ViewModel {

    private final FavoriteRepository mFavoriteRepository;
    private final MutableLiveData<String> _username = new MutableLiveData<>();
    public final LiveData<String> username = _username;

    private final MutableLiveData<String> _name = new MutableLiveData<>();
    public final LiveData<String> name = _name;

    private final MutableLiveData<String> _avatarUrl = new MutableLiveData<>();
    public final LiveData<String> avatarUrl = _avatarUrl;

    private final MutableLiveData<Integer> _following = new MutableLiveData<>();
    public final LiveData<Integer> following = _following;

    private final MutableLiveData<Integer> _followers = new MutableLiveData<>();
    public final LiveData<Integer> followers = _followers;

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public final LiveData<Boolean> isLoading = _isLoading;

    public DetailViewModel(String username, Application app) {
        mFavoriteRepository = new FavoriteRepository(app);
        findUserDetails(username);
    }

    public void findUserDetails(String username) {
        getUserDetail(username);
    }

    public void insert(FavoriteEntity favEntity) {
        mFavoriteRepository.insert(favEntity);
    }

    public void delete(FavoriteEntity favEntity) {
        mFavoriteRepository.delete(favEntity);
    }

    public LiveData<List<FavoriteEntity>> getFavoriteByUsername(String username) {
        return mFavoriteRepository.getUserFavoriteByUsername(username);
    }

    private void getUserDetail(String username) {
        _isLoading.setValue(true);
        Call<ResponseGithubDetail> userDetails = ApiConfig.getApiService().getDetailUser(username);
        userDetails.enqueue(new Callback<ResponseGithubDetail>() {
            @Override
            public void onResponse(Call<ResponseGithubDetail> call, Response<ResponseGithubDetail> response) {
                _isLoading.setValue(false);
                if (response.isSuccessful()) {
                    _name.setValue(response.body().getName());
                    _username.setValue(response.body().getLogin());
                    _avatarUrl.setValue(response.body().getAvatarUrl());
                    _followers.setValue(response.body().getFollowers());
                    _following.setValue(response.body().getFollowing());
                } else {
                    Log.e(ContentValues.TAG, "onFailure: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseGithubDetail> call, Throwable t) {
                _isLoading.setValue(false);
                Log.e(ContentValues.TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
