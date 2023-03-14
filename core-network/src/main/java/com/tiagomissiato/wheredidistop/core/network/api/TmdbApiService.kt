package com.tiagomissiato.wheredidistop.core.network.api

import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResult
import com.tiagomissiato.wheredidistop.core.network.model.response.PopularMovieResponse
import com.tiagomissiato.wheredidistop.core.network.model.response.PopularTvShowResponse
import retrofit2.http.GET

interface TmdbApiService {

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): NetworkResult<PopularMovieResponse>

    @GET("/3/tv/popular")
    suspend fun getPopularTvShow(): NetworkResult<PopularTvShowResponse>

}