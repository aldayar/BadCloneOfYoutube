package com.example.badcloneofyoutube.ui.detailplaylistactitvity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.badcloneofyoutube.core.UIState
import com.example.badcloneofyoutube.databinding.ActivityPlaylistDeteilBinding
import com.example.badcloneofyoutube.ui.activty_main.PlaylistViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaylistDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlaylistDeteilBinding
    private val adapter: PlaylistDetailAdapter by lazy { PlaylistDetailAdapter() }
    private val playlistViewModel by lazy { ViewModelProvider(this)[PlaylistViewModel::class.java] }
    private lateinit var playlistId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaylistDeteilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recyclerView.adapter = adapter

        playlistId = intent.getStringExtra("playlistId") ?: ""

        fetchData()

    }

    private fun fetchData() {
        playlistViewModel.getDetailPlaylist(playlistId)
        playlistViewModel.playlistDetail.observe(this) {
            when (it) {
                is UIState.Loading -> {
                    Toast.makeText(this, "Loading..", Toast.LENGTH_SHORT).show()
                }
                is UIState.Success -> {
                    val playlistItems = it.data?.items
                    adapter.submitList(playlistItems)
                    binding.tvVideoCount.text = it.data?.items?.size.toString()
                    if (!playlistItems.isNullOrEmpty()) {
                        binding.tvTitle.text= playlistItems[0]?.snippet?.title
                        binding.tvDesc.text= playlistItems[0]?.snippet?.description
                    }
                }
                is UIState.Error -> {
                    Log.e("oloolo", it.msg.toString())
                }
            }
        }
    }
}