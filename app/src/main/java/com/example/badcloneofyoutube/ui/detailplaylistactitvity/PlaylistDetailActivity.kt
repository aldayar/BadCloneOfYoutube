package com.example.badcloneofyoutube.ui.detailplaylistactitvity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.badcloneofyoutube.core.UIState
import com.example.badcloneofyoutube.databinding.ActivityPlaylistDeteilBinding
import com.example.badcloneofyoutube.ui.activty_main.PLaylistViewModel
import com.example.badcloneofyoutube.ui.activty_main.PlaylistAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaylistDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlaylistDeteilBinding
    private  val adapter: PlaylistDetailAdapter by lazy { PlaylistDetailAdapter() }
    private val playlistViewModel by lazy { ViewModelProvider(this)[PLaylistViewModel::class.java] }
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
    private fun getPlaylistDetail() {
        playlistViewModel.playlistDetail.observe(this) {
            when (it) {
                is UIState.Loading -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }
                is UIState.Success -> {
                    val playlistItems = it.data?.items
                    adapter.submitList(playlistItems)
                }
                is UIState.Error -> {
                    Log.e( "ololo",it.msg.toString() )
                }
            }
        }
    }
    private fun fetchData() {
        playlistViewModel.getDatailPlaylist(playlistId)
        getPlaylistDetail()
    }
}