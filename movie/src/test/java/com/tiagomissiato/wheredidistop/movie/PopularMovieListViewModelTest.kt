package com.tiagomissiato.wheredidistop.movie

import com.tiagomissiato.wheredidistop.movie.domain.MovieRepository
import com.tiagomissiato.wheredidistop.movie.domain.GetPopularMovieListUseCase
import com.tiagomissiato.wheredidistop.core.testing.BaseViewModelTest
import com.tiagomissiato.wheredidistop.movie.popular.presenter.PopularMovieListViewModel
import com.tiagomissiato.wheredidistop.movie.popular.presenter.PopularMovieUiState
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PopularMovieListViewModelTest: BaseViewModelTest() {

    @MockK
    lateinit var movieRepository: MovieRepository

    @Test
    fun getPopularMovie_whenEmptyList_showEmptyState() = runTest {

        coEvery { movieRepository.getRemotePopularMovies() } returns emptyList()
        coEvery { movieRepository.getCachedPopularMovies() } returns emptyList()

        val viewModel = PopularMovieListViewModel(
            GetPopularMovieListUseCase(movieRepository),
            mainDispatcherRule.testDispatcher
        )

        collectUiStates(viewModel) { stateList ->
            viewModel.getPopularList()

            assertTrue(stateList[0] is PopularMovieUiState.Empty)
            assertTrue(stateList[1] is PopularMovieUiState.Loading)
            assertTrue(stateList[2] is PopularMovieUiState.Empty)
        }
    }

    @Test
    fun getPopularMovie_whenNotEmptyListNotCached_showSuccessStateRemoteList() = runTest {

        coEvery { movieRepository.getRemotePopularMovies() } returns List(5) { mockk() }
        coEvery { movieRepository.getCachedPopularMovies() } returns emptyList()

        val viewModel = PopularMovieListViewModel(
            GetPopularMovieListUseCase(movieRepository),
            mainDispatcherRule.testDispatcher
        )

        collectUiStates(viewModel) { stateList ->
            viewModel.getPopularList()

            assertTrue(stateList[0] is PopularMovieUiState.Empty)
            assertTrue(stateList[1] is PopularMovieUiState.Loading)
            assertTrue(stateList[2] is PopularMovieUiState.Success)
            assertEquals((stateList[2] as PopularMovieUiState.Success).movieList.size, 5)
        }
    }

    @Test
    fun getPopularMovie_whenNotEmptyListCached_showSuccessStateCachedList() = runTest {

        coEvery { movieRepository.getRemotePopularMovies() } returns List(5) { mockk() }
        coEvery { movieRepository.getCachedPopularMovies() } returns List(10) { mockk() }

        val viewModel = PopularMovieListViewModel(
            GetPopularMovieListUseCase(movieRepository),
            mainDispatcherRule.testDispatcher
        )

        collectUiStates(viewModel) { stateList ->
            viewModel.getPopularList()

            assertTrue(stateList[0] is PopularMovieUiState.Empty)
            assertTrue(stateList[1] is PopularMovieUiState.Loading)
            assertTrue(stateList[2] is PopularMovieUiState.Success)
            assertEquals((stateList[2] as PopularMovieUiState.Success).movieList.size, 10)
        }
    }

    @Test
    fun getPopularMovie_whenException_showErrorState() = runTest {

        val useCaseMock = mockk<GetPopularMovieListUseCase>()

        coEvery { useCaseMock(any()) } returns flow {
            throw Exception("Error")
        }

        val viewModel = PopularMovieListViewModel(
            useCaseMock,
            mainDispatcherRule.testDispatcher
        )

        collectUiStates(viewModel) { stateList ->
            viewModel.getPopularList()

            assertTrue(stateList[0] is PopularMovieUiState.Empty)
            assertTrue(stateList[1] is PopularMovieUiState.Loading)
            assertTrue(stateList[2] is PopularMovieUiState.Error)
            assertEquals((stateList[2] as PopularMovieUiState.Error).message, "Error")
        }

    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun TestScope.collectUiStates(vm: PopularMovieListViewModel, block: (stateList: List<PopularMovieUiState>) -> Unit) {
    val listState = mutableListOf<PopularMovieUiState>()
    val collectJob =
        launch(UnconfinedTestDispatcher()) { vm.uiState.toList(listState) }
    block(listState)
    collectJob.cancel()
}