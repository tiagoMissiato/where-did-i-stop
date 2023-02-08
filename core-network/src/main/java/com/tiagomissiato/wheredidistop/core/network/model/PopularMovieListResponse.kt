package com.tiagomissiato.wheredidistop.core.network.model

import com.squareup.moshi.Json

data class PopularMovieListResponse(
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "results")
    val list: List<PopularMovieResponse>,
    @field:Json(name = "total_pages")
    val totalPages: Long,
    @field:Json(name = "total_results")
    val totalResults: Long,
)

data class PopularMovieResponse(
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "adult")
    val adult: Boolean,
    @field:Json(name = "backdrop_path")
    val backdropPath: String,
    @field:Json(name = "genre_ids")
    val genreIds: List<Long>,
    @field:Json(name = "original_language")
    val originalLanguage: String,
    @field:Json(name = "original_title")
    val originalTitle: String,
    @field:Json(name = "overview")
    val overview: String,
    @field:Json(name = "popularity")
    val popularity: Double,
    @field:Json(name = "poster_path")
    val posterPath: String,
    @field:Json(name = "release_date")
    val releaseDate: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "video")
    val video: Boolean,
    @field:Json(name = "vote_average")
    val voteAverage: Double,
    @field:Json(name = "vote_count")
    val voteCount: Long,
)