package com.example.badcloneofyoutube.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.badcloneofyoutube.core.UIState
import com.example.badcloneofyoutube.data.itemmodel.PlaylistItemResponse
import com.example.badcloneofyoutube.data.model.PlaylistModel
import com.example.badcloneofyoutube.data.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {
  fun getPlaylist(): MutableLiveData<UIState<PlaylistModel>> {
    val liveData = MutableLiveData<UIState<PlaylistModel>>()
    liveData.value = UIState.Loading()

    apiService.getPlaylists().enqueue(object : Callback<PlaylistModel> {
      override fun onResponse(call: Call<PlaylistModel>, response: Response<PlaylistModel>) {
        if (response.isSuccessful) {
          val playlistResponse = response.body()
          liveData.value = UIState.Success(playlistResponse)
        } else {
          liveData.value = UIState.Error("Unsuccessful request, try again")
        }
      }

      override fun onFailure(call: Call<PlaylistModel>, t: Throwable) {
        liveData.value = UIState.Error(t.localizedMessage ?: "ERROR")
        Log.e("ololo", "onFailure: ${t.message.toString()}")
      }
    })

    return liveData
  }

  fun getDetailPlaylist(playlistId: String): MutableLiveData<UIState<PlaylistItemResponse>> {
    val liveData = MutableLiveData<UIState<PlaylistItemResponse>>()
    liveData.value = UIState.Loading()
    apiService.getPlaylistDetail(playlistId = playlistId)
      .enqueue(object : Callback<PlaylistItemResponse> {
        override fun onResponse(
          call: Call<PlaylistItemResponse>,
          response: Response<PlaylistItemResponse>
        ) {
            if (response.isSuccessful) {
              val playlistResponse = response.body()
              liveData.value = UIState.Success(playlistResponse)
            } else {
              liveData.value = UIState.Error("Unsuccessful request, try again")
            }
          }
        override fun onFailure(call: Call<PlaylistItemResponse>, t: Throwable) {
          liveData.value = UIState.Error(t.localizedMessage ?: "Current Error")
        }
      })
    return liveData
  }
}