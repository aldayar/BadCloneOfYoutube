package com.example.badcloneofyoutube.ui.activty_main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.badcloneofyoutube.core.UIState
import com.example.badcloneofyoutube.data.itemmodel.PlylistItemResponse
import com.example.badcloneofyoutube.data.model.PlaylistModel
import com.example.badcloneofyoutube.data.repository.Repository
import com.example.badcloneofyoutube.data.videomodel.VideoResponce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _playlistLiveData = MutableLiveData<UIState<PlaylistModel>>()
    val playlistLiveData: LiveData<UIState<PlaylistModel>> = _playlistLiveData

    private val _playlistDetail = MutableLiveData<UIState<PlylistItemResponse>>()
    val playlistDetail: LiveData<UIState<PlylistItemResponse>> = _playlistDetail

    private val _playlistVideo = MutableLiveData<UIState<VideoResponce>>()
    val video: LiveData<UIState<VideoResponce>> = _playlistVideo

    fun getPlaylists() {
        _playlistLiveData.value = UIState.Loading()

        viewModelScope.launch {
            val result = repository.getPlaylist()
            _playlistLiveData.value = result
        }
    }

    fun getDetailPlaylist(playlistId: String) {
        viewModelScope.launch {
            _playlistDetail.value = UIState.Loading()
            val result = repository.getDetailPlaylist(playlistId)
            _playlistDetail.value = result
        }
    }
    fun getVideo(videoId: String) {
        viewModelScope.launch {
            _playlistVideo.value = UIState.Loading()
            val result = repository.getVideo(videoId)
            _playlistVideo.value = result
        }
    }
}
