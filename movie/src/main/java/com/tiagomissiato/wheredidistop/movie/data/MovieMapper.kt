package com.tiagomissiato.wheredidistop.movie.data

import com.tiagomissiato.wheredidistop.core.database.entity.MovieEntity
import com.tiagomissiato.wheredidistop.core.network.model.response.MovieResponse
import com.tiagomissiato.wheredidistop.movie.data.model.Movie

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

fun MovieResponse.toEntity() = MovieEntity(
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