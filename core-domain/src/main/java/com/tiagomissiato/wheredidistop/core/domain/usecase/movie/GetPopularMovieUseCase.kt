package com.tiagomissiato.wheredidistop.core.domain.usecase.movie

import com.tiagomissiato.wheredidistop.core.domain.repository.movie.MovieRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    var number: Int? = null

    operator fun invoke(refresh: Boolean)  = flow {
        number?.also {

        }
        val cachedMovie = if (refresh) {
            movieRepository.clearCache()
            emptyList()
        } else {
            movieRepository.getCachedPopularMovies()
        }

        emit(cachedMovie.ifEmpty {
            movieRepository.getRemotePopularMovies()
        })
    }
}