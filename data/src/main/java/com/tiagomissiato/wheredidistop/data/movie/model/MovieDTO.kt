package com.tiagomissiato.wheredidistop.data.movie.model

data class MovieDTO(
    val id: Long,
    val adult: Boolean,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
)