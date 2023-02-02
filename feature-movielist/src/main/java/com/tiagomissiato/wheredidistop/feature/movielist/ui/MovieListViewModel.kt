/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tiagomissiato.wheredidistop.feature.movielist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.tiagomissiato.wheredidistop.core.data.MovieListRepository
import com.tiagomissiato.wheredidistop.feature.movielist.ui.MovieListUiState.Error
import com.tiagomissiato.wheredidistop.feature.movielist.ui.MovieListUiState.Loading
import com.tiagomissiato.wheredidistop.feature.movielist.ui.MovieListUiState.Success
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository
) : ViewModel() {

    val uiState: StateFlow<MovieListUiState> = movieListRepository
        .movieLists.map { Success(data = it) }
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    fun addMovieList(name: String) {
        viewModelScope.launch {
            movieListRepository.add(name)
        }
    }
}

sealed interface MovieListUiState {
    object Loading : MovieListUiState
    data class Error(val throwable: Throwable) : MovieListUiState
    data class Success(val data: List<String>) : MovieListUiState
}