package com.example.badcloneofyoutube.ui.activty_main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.badcloneofyoutube.data.model.Item
import com.example.badcloneofyoutube.databinding.ItemRcBinding
import com.example.badcloneofyoutube.utils.loadImage

class PlaylistAdapter :
    androidx.recyclerview.widget.ListAdapter<Item, PlaylistAdapter.PlayListViewHolder>(
        PlaylistDiffCallback()
    ) {
    class PlaylistDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }


    }

    class PlayListViewHolder(private val binding: ItemRcBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(playlists: Item) {
            binding.descriptionTextView.text = playlists.snippet?.description
            binding.titleTextView.text = playlists.snippet?.title

            binding.imageView.loadImage(playlists.snippet?.thumbnails?.standard?.url!!)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PlayListViewHolder(
        ItemRcBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        val playlists = getItem(position)
        holder.bind(playlists)
    }
}

