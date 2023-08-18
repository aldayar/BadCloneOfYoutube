package com.example.badcloneofyoutube.data.remote

import com.example.badcloneofyoutube.BuildConfig
import com.example.badcloneofyoutube.data.model.PlaylistModel
import com.example.badcloneofyoutube.utils.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlists")
    fun getPlaylists(
        @Query("part") part: String = Constants.PART,
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("channelId") channelId: String = Constants.CHANNEL_ID
    ): Call<PlaylistModel>
}
