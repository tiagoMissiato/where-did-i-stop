package com.tiagomissiato.wheredidistop.core.domain.movie

import com.tiagomissiato.wheredidistop.core.model.dto.Movie
import com.tiagomissiato.wheredidistop.core.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface GetPopularMovieListUseCase {
    fun getPopularMovieList(refresh: Boolean): Flow<List<Movie>>
}

class GetPopularMovieListUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
): GetPopularMovieListUseCase {

    override fun getPopularMovieList(refresh: Boolean) = flow {
        emit(movieRepository.getPopularMovieList(refresh))
    }
}