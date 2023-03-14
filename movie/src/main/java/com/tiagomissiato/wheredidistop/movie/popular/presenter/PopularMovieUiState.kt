package com.tiagomissiato.wheredidistop.movie.popular.presenter

import com.tiagomissiato.wheredidistop.movie.data.model.Movie

sealed class PopularMovieUiState {
    object Empty : PopularMovieUiState()
    object Loading : PopularMovieUiState()
    object LoadingMore : PopularMovieUiState()
    object Refreshing : PopularMovieUiState()
    data class Success(val movieList: List<Movie>) : PopularMovieUiState()
    data class Error(val message: String) : PopularMovieUiState()
}