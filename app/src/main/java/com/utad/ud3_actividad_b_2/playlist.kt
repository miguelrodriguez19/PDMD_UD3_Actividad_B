package com.utad.ud3_actividad_b_2

import java.io.Serializable

data class PlaylistsResponse(
    val `data`: kotlin.collections.List<Playlist>
)

data class Playlist(
    val followers: Int,
    val id: Int,
    val songs: kotlin.collections.List<Song>,
    val tittle: String,
    val url_photo: String
) : Serializable

data class Song(
    val autor: String,
    val id: Int,
    val tittle: String,
    val url_photo: String
)
