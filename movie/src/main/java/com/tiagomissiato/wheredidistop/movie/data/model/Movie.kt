package com.tiagomissiato.wheredidistop.movie.data.model

import com.tiagomissiato.wheredidistop.ui.component.card.vo.CardMovieItemVo

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

fun Movie.toVo() = CardMovieItemVo(
    id = this.id,
    voteAverage = this.voteAverage,
    adult = this.adult,
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    overview = this.overview,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    title = this.title
)