package com.tiagomissiato.wheredidistop.core.network.api

import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResult
import com.tiagomissiato.wheredidistop.core.network.model.PopularMovieListResponse
import retrofit2.Response
import retrofit2.http.GET

interface TmdbApiService {

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): NetworkResult<PopularMovieListResponse>

}