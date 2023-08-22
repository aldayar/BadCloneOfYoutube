package com.example.badcloneofyoutube.ui.activty_main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.badcloneofyoutube.R
import com.example.badcloneofyoutube.core.UIState
import com.example.badcloneofyoutube.databinding.ActivityMainBinding
import com.example.badcloneofyoutube.ui.detailplaylistactitvity.PlaylistDetailActivity
import com.example.badcloneofyoutube.ui.internetobserveractivity.InternetObserverActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), InternetObserverActivity.InternetCallback,
    PlaylistAdapter.OnClickListener {

    private val internetObserver by lazy { InternetObserverActivity(this, this) }
    private lateinit var binding: ActivityMainBinding
    private  val adapter by lazy { PlaylistAdapter(this) }
    private val viewModel by lazy { ViewModelProvider(this)[PLaylistViewModel::class.java] }
    private var isConnected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        internetObserver.checkInternet()

        binding.recyclerView.adapter = adapter

        fetchData()
    }

    private fun getPlaylist() {
        viewModel.playlistLiveData.observe(this) {
            when (it) {
                is UIState.Loading -> {
                    Toast.makeText(this, "Loading, please wait", Toast.LENGTH_SHORT).show()
                }
                is UIState.Success -> {
                    adapter.submitList(it.data?.items)
                }
                is UIState.Error -> {
                    Toast.makeText(this, "Connection error", Toast.LENGTH_SHORT).show()
                    Log.e("ololo", "API call failed: ${it.msg.toString()}")
                }
            }
        }
    }

    private fun fetchData() {
        viewModel.getPlaylists()
        getPlaylist()
    }

    override fun onInternetAvailable() {
        if (!isConnected) {
            isConnected = true
            binding.noConnectionLayout.root.visibility = View.GONE
            fetchData()
        }
    }

    override fun onInternetUnavailable() {
        binding.noConnectionLayout.root.visibility = View.VISIBLE
        val recheckButton = findViewById<Button>(R.id.btn_try_Again)
        recheckButton.setOnClickListener {
            internetObserver.checkInternet()
        }
    }

    override fun onClick(model: String?) {
        val intent = Intent(this, PlaylistDetailActivity::class.java)
        intent.putExtra("playlistId", model)
        startActivity(intent)
    }
}
