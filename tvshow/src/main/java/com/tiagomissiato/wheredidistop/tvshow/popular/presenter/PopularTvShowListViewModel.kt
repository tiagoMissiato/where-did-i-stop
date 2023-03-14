package com.tiagomissiato.wheredidistop.tvshow.popular.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiagomissiato.wheredidistop.tvshow.popular.domain.GetPopularTvShowUseCase
import com.tiagomissiato.wheredidistop.tvshow.popular.data.model.TvShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PopularTvShowUiState(
    val tvShowList: List<TvShow> = emptyList()
)

@HiltViewModel
class PopularTvShowListViewModel @Inject constructor(
    private val popularTvShowUseCase: GetPopularTvShowUseCase
) : ViewModel() {
    // Game UI state
    private val _uiState = MutableStateFlow(PopularTvShowUiState())
    val uiState: StateFlow<PopularTvShowUiState> = _uiState.asStateFlow()

    fun getPopularList() {
        viewModelScope.launch {
            popularTvShowUseCase().collect { list ->
                _uiState.value = PopularTvShowUiState(tvShowList = list)
            }
        }
    }

}