package com.tiagomissiato.wheredidistop.core.network.model.response

import com.squareup.moshi.Json

data class PopularTvShowResponse(
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "results")
    val list: List<TvShowResponse>,
    @field:Json(name = "total_pages")
    val totalPages: Long,
    @field:Json(name = "total_results")
    val totalResults: Long,
)