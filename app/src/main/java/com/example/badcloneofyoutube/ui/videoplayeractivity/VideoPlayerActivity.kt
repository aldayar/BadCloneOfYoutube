package com.example.badcloneofyoutube.ui.videoplayeractivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.badcloneofyoutube.core.UIState
import com.example.badcloneofyoutube.databinding.ActivityVideoPlayerBinding
import com.example.badcloneofyoutube.ui.activty_main.PlaylistViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoPlayerActivity : AppCompatActivity() {
    private lateinit var videoId: String
    private lateinit var binding: ActivityVideoPlayerBinding
    private val videoViewModel by lazy { ViewModelProvider(this)[PlaylistViewModel::class.java] }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        videoId = intent.getStringExtra("videoId") ?: ""
        binding.webview.settings.javaScriptEnabled = true


        val youtubeEmbedUrl = "https://www.youtube.com/embed/$videoId"
        binding.webview.loadUrl(youtubeEmbedUrl)

        fetchData()

    }
    private fun fetchData(){
        videoViewModel.getVideo(videoId)
        videoViewModel.video.observe(this) {
            when (it) {
                is UIState.Loading -> {
                    Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
                }
                is UIState.Success -> {
                    val videoDetails = it.data
                    val videoUrl =
                        videoDetails?.items?.firstOrNull()?.snippet?.thumbnails?.default?.url

                    binding.tvVideoTitle.text =
                        videoDetails?.items?.firstOrNull()?.snippet?.title
                    binding.tvVideoDesc.text =
                        videoDetails?.items?.firstOrNull()?.snippet?.description

                    if (!videoUrl.isNullOrEmpty()) {
                        binding.webview.webViewClient = object : WebViewClient() {
                            override fun shouldOverrideUrlLoading(
                                view: WebView?,
                                url: String
                            ): Boolean {
                                view?.loadUrl(url)
                                return true
                            }
                        }
                    }
                }
                is UIState.Error -> {
                    Toast.makeText(this, "Error loading video", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}