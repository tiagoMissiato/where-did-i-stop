package com.tiagomissiato.wheredidistop.movie.popular

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiagomissiato.wheredidistop.data.movie.model.MovieDTO
import com.tiagomissiato.wheredidistop.usecase.movie.GetPopularMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PopularMovieUiState(
    val movieList: List<MovieDTO> = emptyList()
)

@HiltViewModel
class PopularMovieListViewModel @Inject constructor(
    private val popularMovieUseCase: GetPopularMovieListUseCase
) : ViewModel() {
    // Game UI state
    private val _uiState = MutableStateFlow(PopularMovieUiState())
    val uiState: StateFlow<PopularMovieUiState> = _uiState.asStateFlow()

    fun getPopularList() {
        viewModelScope.launch {
            popularMovieUseCase.getPopularMovieList().collect { list ->
                _uiState.value = PopularMovieUiState(movieList = list)
            }
        }
    }

}