package com.tiagomissiato.wheredidistop.core.model.response

import com.squareup.moshi.Json

data class PopularMovieResponse(
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "results")
    val list: List<MovieResponse>,
    @field:Json(name = "total_pages")
    val totalPages: Long,
    @field:Json(name = "total_results")
    val totalResults: Long,
)