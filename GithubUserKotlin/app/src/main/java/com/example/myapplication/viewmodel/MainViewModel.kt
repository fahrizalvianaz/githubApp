package com.example.myapplication.viewmodel

import android.content.ContentValues.TAG
import android.util.Log.e
import androidx.lifecycle.*
import com.example.myapplication.data.local.datastore.SettingPreferences
import com.example.myapplication.data.remote.retrofit.ApiConfig
import com.example.myapplication.data.remote.response.ItemsItem
import com.example.myapplication.data.remote.response.ResponseGithub
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val pref: SettingPreferences) : ViewModel() {

    private val _user = MutableLiveData<List<ItemsItem>>()
    val user : LiveData<List<ItemsItem>> = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _username = MutableLiveData<String?>()
    val username : LiveData<String?> = _username

    private val _avatarUrl = MutableLiveData<String?>()
    val avatarUrl : LiveData<String?> = _avatarUrl

    fun findUser(username : String) {
        getUser(username)
    }

    private fun getUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUser(username)
        client.enqueue(object : Callback<ResponseGithub> {
            override fun onResponse(
                call: Call<ResponseGithub>,
                response: Response<ResponseGithub>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    if (response.isSuccessful) {
                        _user.value = response.body()?.items
                    }

                } else {
                    e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseGithub>, t: Throwable) {
                _isLoading.value = false
                e(TAG, "onFailure: ${t.message}")
            }

        })

    }
    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

}

