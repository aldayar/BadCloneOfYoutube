package com.example.badcloneofyoutube.data.itemmodel

data class PlaylistItemResponse(
    val etag: String?,
    val items: List<Item?>?,
    val kind: String?,
    val nextPageToken: String?,
    val pageInfo: PageInfo?
)