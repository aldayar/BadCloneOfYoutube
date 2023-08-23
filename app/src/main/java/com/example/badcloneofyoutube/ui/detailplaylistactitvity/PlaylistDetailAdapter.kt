package com.example.badcloneofyoutube.ui.detailplaylistactitvity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.badcloneofyoutube.data.itemmodel.Item
import com.example.badcloneofyoutube.databinding.ItemPlaylisItemBinding
import com.example.badcloneofyoutube.utils.loadImage

class PlaylistDetailAdapter(val listener: OnClickListener) :
    androidx.recyclerview.widget.ListAdapter<Item, PlaylistDetailAdapter.PlaylistDViewHolder>(
        PlaylistDDiffCallback()
    ) {
    inner class PlaylistDViewHolder(private val binding: ItemPlaylisItemBinding) :
        ViewHolder(binding.root) {
        fun bind(playlistDetail: Item) {
            binding.titleTextView.text = playlistDetail.snippet?.title
            binding.tvVideoTime.text = playlistDetail.snippet?.videoOwnerChannelTitle

            binding.imageView.loadImage(playlistDetail.snippet?.thumbnails?.maxres?.url!!)

            itemView.setOnClickListener{listener.onClick(playlistDetail.snippet.resourceId?.videoId)}

        }

    }

    class PlaylistDDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PlaylistDViewHolder(
        ItemPlaylisItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PlaylistDViewHolder, position: Int) {
        val playlists = getItem(position)
        holder.bind(playlists)
    }
    interface OnClickListener {
        fun onClick(videoId: String?)
    }
}
