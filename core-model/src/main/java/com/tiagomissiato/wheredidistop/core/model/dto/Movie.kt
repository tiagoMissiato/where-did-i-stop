package com.tiagomissiato.wheredidistop.core.model.dto

data class Movie(
    val id: Long,
    val voteAverage: Double,
    val adult: Boolean,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
)