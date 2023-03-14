package com.tiagomissiato.wheredidistop.movie.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiagomissiato.wheredidistop.core.domain.usecase.movie.GetPopularMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PopularMovieListViewModel @Inject constructor(
    private val popularMovieUseCase: GetPopularMovieListUseCase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _uiState = MutableStateFlow<PopularMovieUiState>(PopularMovieUiState.Empty)
    val uiState: StateFlow<PopularMovieUiState> = _uiState.asStateFlow()

    fun getPopularList(refresh: Boolean = false) {
        popularMovieUseCase(refresh)
            .flowOn(ioDispatcher)
            .filter { it.isNotEmpty() }
            .onEach {
                _uiState.value = PopularMovieUiState.Success(it)
            }
            .onStart {
                _uiState.value = PopularMovieUiState.Loading
            }
            .onEmpty {
                _uiState.value = PopularMovieUiState.Empty
            }
            .catch {
                _uiState.value = PopularMovieUiState.Error(it.message ?: "")
            }
            .launchIn(viewModelScope)
    }

}