package com.example.badcloneofyoutube.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(text: String) {
    Glide.with(this).load(text).centerCrop().into(this)
}