package com.example.badcloneofyoutube.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.badcloneofyoutube.BuildConfig.BASE_URL
import com.example.badcloneofyoutube.data.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun ImageView.loadImage(text: String) {
    Glide.with(this).load(text).centerCrop().into(this)
}