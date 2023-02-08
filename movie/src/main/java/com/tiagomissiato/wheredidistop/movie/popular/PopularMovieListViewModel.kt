package com.tiagomissiato.wheredidistop.movie.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiagomissiato.wheredidistop.core.network.api.TmdbApiService
import com.tiagomissiato.wheredidistop.core.network.model.PopularMovieListResponse
import com.tiagomissiato.wheredidistop.core.network.model.PopularMovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMovieListViewModel @Inject constructor(var service: TmdbApiService) : ViewModel() {

    fun getPopularList(): Flow<List<PopularMovieResponse>> {
//        viewModelScope.launch {
            return flow {
                val response = service.getPopularMovies()
                val list = if (response.isSuccessful)
                    response.body()?.list?.let {
                        it
                    } ?: emptyList()
                else emptyList()

                emit(list)
            }
//        }
    }

}