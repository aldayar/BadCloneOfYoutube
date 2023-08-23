package com.example.badcloneofyoutube.data.repository

import com.example.badcloneofyoutube.core.UIState
import com.example.badcloneofyoutube.data.itemmodel.PlylistItemResponse
import com.example.badcloneofyoutube.data.model.PlaylistModel
import com.example.badcloneofyoutube.data.remote.ApiService
import com.example.badcloneofyoutube.data.videomodel.VideoResponce
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService){

suspend fun getPlaylist(): UIState<PlaylistModel> {
  return try {
    val response = apiService.getPlaylists()
    if (response.isSuccessful) {
      val playlistResponse = response.body()
      UIState.Success(playlistResponse)
    } else {
      UIState.Error("Unsuccessful request, try again")
    }
  } catch (e: Exception) {
    UIState.Error(e.localizedMessage ?: "ERROR")
  }
}

  suspend fun getDetailPlaylist(playlistId: String): UIState<PlylistItemResponse> {
    return try {
      val response = apiService.getPlaylistDetail(playlistId = playlistId)
      if (response.isSuccessful) {
        val playlistResponse = response.body()
        UIState.Success(playlistResponse)
      } else {
        UIState.Error("Unsuccessful request, try again")
      }
    } catch (e: Exception) {
      UIState.Error(e.localizedMessage ?: "Current Error")
    }
  }

  suspend fun getVideo(videoId: String): UIState<VideoResponce> {
    return try {
      val response = apiService.getVideo(videoId = videoId)
      if (response.isSuccessful) {
        UIState.Success(response.body())
      } else {
        UIState.Error("Unsuccessful request, try again")
      }
    } catch (e: Exception) {
      UIState.Error(e.localizedMessage ?: "Current Error")
    }
  }
}