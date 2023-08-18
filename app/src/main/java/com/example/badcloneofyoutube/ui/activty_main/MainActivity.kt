package com.example.badcloneofyoutube.ui.activty_main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.badcloneofyoutube.core.UIState
import com.example.badcloneofyoutube.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PlaylistAdapter
    private val viewModel by lazy { ViewModelProvider(this)[MainActivityViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        adapter = PlaylistAdapter()
        binding.recyclerView.adapter = adapter

        fetchData()
    }


    private fun fetchData() {
            viewModel.getPlaylists()
            getPlaylist()
        getPlaylist()


    }

    private fun getPlaylist() {
        viewModel.playlistLiveData.observe(this) {
            when (it) {
                is UIState.Loading -> {
                    Toast.makeText(this, "Loading, please wait", Toast.LENGTH_SHORT)
                        .show()
                }
                is UIState.Success -> {
                    adapter.submitList(it.data?.items)
                }
                is UIState.Error -> {
                    Toast.makeText(
                        this,
                        "connection error ${it.msg.toString()}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    Log.e("API_REQUEST", "API call failed: ${it.msg.toString()}")
                }
            }
        }
    }
}