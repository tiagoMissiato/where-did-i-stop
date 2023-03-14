package com.tiagomissiato.wheredidistop.tvshow.popular.data.model

import com.tiagomissiato.wheredidistop.ui.component.card.vo.CardTvShowItemVo

data class TvShow(
    val id: Long,
    val voteAverage: Double,
    val adult: Boolean,
    val originalLanguage: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
)

fun TvShow.toVo() = CardTvShowItemVo(
    id = this.id,
    voteAverage = this.voteAverage,
    adult = this.adult,
    originalLanguage = this.originalLanguage,
    overview = this.overview,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    title = this.title
)