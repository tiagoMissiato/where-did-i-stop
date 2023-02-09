package com.tiagomissiato.wheredidistop.usecase.movie

import com.tiagomissiato.wheredidistop.data.movie.model.MovieDTO
import com.tiagomissiato.wheredidistop.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface GetPopularMovieListUseCase {
    fun getPopularMovieList(): Flow<List<MovieDTO>>
}

class GetPopularMovieListUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
): GetPopularMovieListUseCase  {

    override fun getPopularMovieList() = flow {
        emit(movieRepository.getPopularMovieList())
    }
}