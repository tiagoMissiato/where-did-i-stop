package com.tiagomissiato.wheredidistop.tvshow.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiagomissiato.wheredidistop.core.domain.movie.GetPopularTvShowListUseCase
import com.tiagomissiato.wheredidistop.core.model.dto.TvShow
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
    private val popularTvShowUseCase: GetPopularTvShowListUseCase
) : ViewModel() {
    // Game UI state
    private val _uiState = MutableStateFlow(PopularTvShowUiState())
    val uiState: StateFlow<PopularTvShowUiState> = _uiState.asStateFlow()

    fun getPopularList() {
        viewModelScope.launch {
            popularTvShowUseCase.getPopularTvShowList().collect { list ->
                _uiState.value = PopularTvShowUiState(tvShowList = list)
            }
        }
    }

}