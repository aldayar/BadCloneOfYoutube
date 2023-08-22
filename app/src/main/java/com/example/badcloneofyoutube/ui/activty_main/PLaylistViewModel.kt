package com.example.badcloneofyoutube.ui.activty_main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.badcloneofyoutube.core.UIState
import com.example.badcloneofyoutube.data.itemmodel.PlaylistItemResponse
import com.example.badcloneofyoutube.data.model.PlaylistModel
import com.example.badcloneofyoutube.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PLaylistViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    var playlistLiveData = MutableLiveData<UIState<PlaylistModel>>()
    var playlistDetail = MutableLiveData<UIState<PlaylistItemResponse>>()

    fun getPlaylists(): LiveData<UIState<PlaylistModel>> {
        playlistLiveData.value = UIState.Loading()

        playlistLiveData = repository.getPlaylist()
        return playlistLiveData

    }

    fun getDatailPlaylist(playlistId: String) {
        playlistDetail = repository.getDetailPlaylist(playlistId)

    }
}
