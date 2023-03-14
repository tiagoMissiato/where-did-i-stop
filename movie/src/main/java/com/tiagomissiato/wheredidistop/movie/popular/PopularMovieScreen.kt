package com.tiagomissiato.wheredidistop.movie.popular

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tiagomissiato.wheredidistop.core.data.mock.popularMovieList
import com.tiagomissiato.wheredidistop.core.model.dto.Movie
import com.tiagomissiato.wheredidistop.core.ui.theme.WhereDidIStopTheme
import com.tiagomissiato.wheredidistop.ui.component.card.CardMovieItem
import com.tiagomissiato.wheredidistop.ui.component.card.ErrorStateScreen

@Composable
fun PopularMovieListScreen(
    modifier: Modifier = Modifier,
    viewModel: PopularMovieListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    PopularMovieList(
       modifier,
       uiState,
        { viewModel.getPopularList(true) },
        { viewModel.getPopularList(true) }
    )

}

@Composable
fun PopularMovieList(
    modifier: Modifier = Modifier,
    uiState: PopularMovieUiState,
    onTryAgain: () -> Unit,
    onRefresh: () -> Unit
) {
    val isRefreshing = uiState is PopularMovieUiState.Loading

    when (uiState) {
        is PopularMovieUiState.Success -> PopulateMovieList(
            modifier,
            uiState.movieList,
            isRefreshing,
            onRefresh
        )
        is PopularMovieUiState.Empty -> PopularMovieErrorState(modifier, onTryAgain)
        is PopularMovieUiState.Error -> PopularMovieErrorState(modifier, onTryAgain)
        PopularMovieUiState.Loading -> {

        }
        PopularMovieUiState.LoadingMore -> {

        }
        PopularMovieUiState.Refreshing -> {

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PopulateMovieList(
    modifier: Modifier = Modifier,
    items: List<Movie>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    val pullToRefresh = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = { onRefresh() })

    Box(Modifier.pullRefresh(pullToRefresh)) {
        Column {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items) { movie ->
                    CardMovieItem(movie = movie)
                }
            }

        }
        PullRefreshIndicator(isRefreshing, pullToRefresh, modifier.align(Alignment.TopCenter))
    }
}

@Composable
fun PopularMovieErrorState(modifier: Modifier, onTryAgain: () -> Unit) {
    Column {
        ErrorStateScreen(tryAgainClick = onTryAgain)
    }
}

@Preview(showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun DefaultPreviewEmpty() {

    WhereDidIStopTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            PopularMovieList(Modifier, PopularMovieUiState.Success(emptyList()), {}, {})
        }
    }
}

@Preview(showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun DefaultPreviewList() {

    WhereDidIStopTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            PopularMovieList(
                Modifier,
                PopularMovieUiState.Success(popularMovieList(10)),
                {},
                {}
            )
        }
    }
}