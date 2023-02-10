package com.tiagomissiato.wheredidistop.movie.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiagomissiato.wheredidistop.core.model.dto.Movie
import com.tiagomissiato.wheredidistop.core.domain.movie.GetPopularMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PopularMovieUiState(
    val movieList: List<Movie> = emptyList()
)

@HiltViewModel
class PopularMovieListViewModel @Inject constructor(
    private val popularMovieUseCase: GetPopularMovieListUseCase,
) : ViewModel() {
    // Game UI state
    private val _uiState = MutableStateFlow(PopularMovieUiState())
    val uiState: StateFlow<PopularMovieUiState> = _uiState.asStateFlow()

    fun getPopularList(refresh: Boolean = false) {
        viewModelScope.launch {
            popularMovieUseCase.getPopularMovieList(refresh).collect { list ->
                _uiState.value = PopularMovieUiState(movieList = list)
            }
        }
    }

}