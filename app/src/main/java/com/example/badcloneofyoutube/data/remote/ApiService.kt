package com.example.badcloneofyoutube.data.remote

import com.example.badcloneofyoutube.BuildConfig
import com.example.badcloneofyoutube.data.itemmodel.PlylistItemResponse
import com.example.badcloneofyoutube.data.model.PlaylistModel
import com.example.badcloneofyoutube.data.videomodel.VideoResponce
import com.example.badcloneofyoutube.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlists")
  suspend  fun getPlaylists(
        @Query("part") part: String = Constants.PART,
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("channelId") channelId: String = Constants.CHANNEL_ID
    ): Response<PlaylistModel>

    @GET("playlistItems")
    suspend fun getPlaylistDetail(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("part") part: String = Constants.PART,
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResults: Int = 20
    ): Response<PlylistItemResponse>
    @GET("videos")
    suspend fun getVideo(
        @Query("id") videoId: String,
        @Query("key") key: String=BuildConfig.API_KEY,
        @Query("part") part: String= Constants.PART
    ): Response<VideoResponce>
}

