package com.tiagomissiato.wheredidistop.movie.popular

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.tiagomissiato.wheredidistop.core.model.dto.Movie
import com.tiagomissiato.wheredidistop.core.ui.theme.WhereDidIStopTheme
import com.tiagomissiato.wheredidistop.movie.R
import com.tiagomissiato.wheredidistop.ui.theme.Typography

@Composable
fun PopularMovieList(
    modifier: Modifier = Modifier,
    viewModel: PopularMovieListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column {
        LoadMovies (
            { viewModel.getPopularList() },
            { viewModel.getPopularList(true) }
        )

        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.movieList) { movie ->
                MovieListItem(movie)
            }
        }
    }
}

@Composable
fun LoadMovies(loadMovie: () -> Unit, clearCache: () -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Button(
            onClick = { loadMovie() }
        ) {
            Text(text = "Load movie")
        }

        Button(
            onClick = { clearCache() }
        ) {
            Text(text = "Clear Cache")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListItem(movie: Movie) {

    Box(modifier = Modifier.clickable { { } }, contentAlignment = Alignment.BottomStart) {
        Card(modifier = Modifier.padding(8.dp), elevation = CardDefaults.cardElevation(4.dp)) {
            Column(modifier = Modifier.padding(start = 100.dp, top = 8.dp, bottom = 8.dp, end = 8.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = movie.title,
                            style = Typography.titleSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = movie.originalTitle,
                            style = Typography.labelSmall,
                            fontStyle = FontStyle.Italic,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row {
                            ElevatedAssistChip(
                                onClick = { { } },
                                label = {
                                    Text(text = movie.releaseDate, style = Typography.labelSmall,)
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.DateRange,
                                        contentDescription = "Localized description",
                                        modifier = Modifier.size(AssistChipDefaults.IconSize),
                                        tint = MaterialTheme.colorScheme.surfaceTint
                                    )
                                }
                            )
                            Divider(modifier = Modifier.width(8.dp))
                            ElevatedAssistChip(
                                onClick = { { } },
                                label = {
                                    Text(text = movie.originalLanguage, style = Typography.labelSmall,)
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Language,
                                        contentDescription = "Localized description",
                                        modifier = Modifier.size(AssistChipDefaults.IconSize),
                                        tint = MaterialTheme.colorScheme.surfaceTint
                                    )
                                }
                            )
                        }
                    }
                    Text(
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .padding(start = 4.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        text = movie.voteAverage.toString(),
                        style = Typography.titleMedium,
                    )
                }
            }
        }
        AsyncImage(
            modifier = Modifier
                .height(130.dp)
                .padding(start = 24.dp)
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
            contentDescription = "description",
            placeholder = painterResource(R.drawable.mad_max_poster)
        )

    }
}

@Preview(showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun DefaultPreview() {
    WhereDidIStopTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            MovieListItem(Movie(
                id = 1,
                adult = false,
                voteAverage = 5.6,
                originalLanguage = "en",
                originalTitle = "Black Panther: Wakanda Forever",
                overview = "Queen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’Baku,",
                posterPath = "/sv1xJUazXeYqALzczSZ3O6nkH75.jpg",
                releaseDate = "2022-11-09",
                title = "Black Panther: Wakanda ForeverBlack Panther: Wakanda Forever"
            ))
        }
    }
}

@Preview(showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun DefaultPreviewLoadMore() {
    WhereDidIStopTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            PopularMovieList()
        }
    }
}