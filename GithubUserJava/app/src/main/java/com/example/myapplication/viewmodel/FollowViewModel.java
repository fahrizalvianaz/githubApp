package com.example.myapplication.viewmodel;

import android.content.ContentValues;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.myapplication.data.remote.response.ItemsItem;
import com.example.myapplication.data.remote.retrofit.ApiConfig;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowViewModel extends ViewModel {

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public final LiveData<Boolean> isLoading = _isLoading;

    private final MutableLiveData<List<ItemsItem>> _follow = new MutableLiveData<>();
    public final LiveData<List<ItemsItem>> follow = _follow;

    private final MutableLiveData<Integer> _following = new MutableLiveData<>();
    public final LiveData<Integer> following = _following;

    private final MutableLiveData<Integer> _followers = new MutableLiveData<>();
    public final LiveData<Integer> followers = _followers;

    public void findUserFollowing(String username) {
        getUserFollowing(username);
    }

    public void findUserFollowers(String username) {
        getUserFollowers(username);
    }

    public FollowViewModel(String username) {
        findUserFollowers(username);
        findUserFollowing(username);
    }

    private void getUserFollowers(String username) {
        _isLoading.setValue(true);
        Call<List<ItemsItem>> userFollowers = ApiConfig.getApiService().getFollowers(username);

        userFollowers.enqueue(new Callback<List<ItemsItem>>() {
            @Override
            public void onResponse(Call<List<ItemsItem>> call, Response<List<ItemsItem>> response) {
                if (response.isSuccessful()) {
                    _isLoading.setValue(false);
                    _follow.setValue(response.body());
                } else {
                    Log.e(ContentValues.TAG, "onFailure: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ItemsItem>> call, Throwable t) {
                _isLoading.setValue(false);
                Log.e(ContentValues.TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void getUserFollowing(String username) {
        _isLoading.setValue(true);
        Call<List<ItemsItem>> userFollowing = ApiConfig.getApiService().getFollowing(username);

        userFollowing.enqueue(new Callback<List<ItemsItem>>() {
            @Override
            public void onResponse(Call<List<ItemsItem>> call, Response<List<ItemsItem>> response) {
                if (response.isSuccessful()) {
                    _isLoading.setValue(false);
                    _follow.setValue(response.body());
                } else {
                    Log.e(ContentValues.TAG, "onFailure: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ItemsItem>> call, Throwable t) {
                _isLoading.setValue(false);
                Log.e(ContentValues.TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
