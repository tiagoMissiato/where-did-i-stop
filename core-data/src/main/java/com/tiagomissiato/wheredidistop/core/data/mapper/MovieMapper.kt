package com.tiagomissiato.wheredidistop.core.data.mapper

import com.tiagomissiato.wheredidistop.core.database.entity.MovieEntity
import com.tiagomissiato.wheredidistop.core.model.dto.Movie
import com.tiagomissiato.wheredidistop.core.model.response.MovieResponse

fun MovieEntity.toDto() = Movie(
    id = this.id,
    adult = this.adult,
    voteAverage = this.voteAverage,
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    overview = this.overview,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    title = this.title
)

fun MovieResponse.toDto() = Movie(
    id = this.id,
    adult = this.adult,
    voteAverage = this.voteAverage,
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    overview = this.overview,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    title = this.title
)

fun Movie.toEntity() = MovieEntity(
    id = this.id,
    adult = this.adult,
    voteAverage = this.voteAverage,
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    overview = this.overview,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    title = this.title
)