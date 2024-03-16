package com.example.myapplication.viewmodel

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.local.entity.FavoriteEntity
import com.example.myapplication.data.remote.response.ResponseGithubDetail
import com.example.myapplication.data.remote.retrofit.ApiConfig
import com.example.myapplication.repository.FavoriteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(username: String,app: Application) : ViewModel() {

    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(app)
    private val _username = MutableLiveData<String?>()
    val username : LiveData<String?> = _username

    private val _name = MutableLiveData<String?>()
    val name : LiveData<String?> = _name

    private val _avatarUrl = MutableLiveData<String?>()
    val avatarUrl : LiveData<String?> = _avatarUrl

    private val _following = MutableLiveData<Int?>()
    val following : LiveData<Int?> = _following

    private val _followers = MutableLiveData<Int?>()
    val followers : LiveData<Int?> = _followers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun findUserDetails(username: String) {
        getUserDetail(username)
    }

    fun insert(favEntity: FavoriteEntity) {
        mFavoriteRepository.insert(favEntity)
    }

    fun delete(favEntity: FavoriteEntity) {
        mFavoriteRepository.delete(favEntity)
    }

    fun getFavoriteByUsername(username: String ): LiveData<List<FavoriteEntity>> {
        return mFavoriteRepository.getUserFavoriteByUsername(username)
    }

    private fun getUserDetail(username: String) {
        _isLoading.value = true
        val userDetails = ApiConfig.getApiService().getDetailUser(username)
        userDetails.enqueue(object : Callback<ResponseGithubDetail> {
            override fun onResponse(
                call: Call<ResponseGithubDetail>,
                response: Response<ResponseGithubDetail>
            ) {
                _isLoading.value = false
                if(response.isSuccessful) {
                    _name.value = response.body()?.name
                    _username.value = response.body()?.login
                    _avatarUrl.value = response.body()?.avatarUrl
                    _followers.value = response.body()?.followers
                    _following.value = response.body()?.following
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseGithubDetail>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })

    }
}