package com.tiagomissiato.wheredidistop.movie.domain

import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    var number: Int? = null

    operator fun invoke(refresh: Boolean) =
        movieRepository.getPopularMovies()
}