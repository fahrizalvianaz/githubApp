package com.example.myapplication.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite")
data class FavoriteEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "username")
    var username : String = "",

    @ColumnInfo(name = "avatar_url") 
    var avatarUrl : String? = null
) : Parcelable