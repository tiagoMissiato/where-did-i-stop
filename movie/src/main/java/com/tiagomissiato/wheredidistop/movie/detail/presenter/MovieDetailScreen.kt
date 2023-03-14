package com.tiagomissiato.wheredidistop.movie.detail.presenter

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tiagomissiato.wheredidistop.core.ui.theme.WhereDidIStopTheme
import com.tiagomissiato.wheredidistop.movie.data.model.Movie
import com.tiagomissiato.wheredidistop.ui.component.R
import com.tiagomissiato.wheredidistop.ui.theme.Typography

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    val scrollState = rememberScrollState()
    Box {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .blur(radius = 5.dp).graphicsLayer {
                    translationY = -0.5f * scrollState.value
                },
            contentScale = ContentScale.FillWidth,
            model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
            contentDescription = "description",
            placeholder = painterResource(R.drawable.bg_test)
        )
        Box(modifier = Modifier
            .verticalScroll(scrollState)
            .padding(top = 128.dp), contentAlignment = Alignment.TopStart) {
            Card(modifier = Modifier.padding(top = 32.dp), elevation = CardDefaults.cardElevation(4.dp)) {
                Column(modifier = Modifier.padding(start = 120.dp, top = 8.dp, bottom = 8.dp, end = 8.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier
                            .weight(1f)
                            .height(2000.dp)) {
                            Text(
                                text = movie.title,
                                style = Typography.titleSmall,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
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
                    .clip(RoundedCornerShape(8.dp))
                    ,
                model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                contentDescription = "description",
                placeholder = painterResource(R.drawable.mad_max_poster)
            )

        }

    }

}

@Preview(showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewCardMovieItem() {
    WhereDidIStopTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            MovieDetailScreen(Modifier, Movie(
                id = 1,
                adult = false,
                voteAverage = 5.6,
                originalLanguage = "en",
                originalTitle = "Black Panther: Wakanda Forever",
                overview = "Queen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’Baku,",
                posterPath = "/sv1xJUazXeYqALzczSZ3O6nkH75.jpg",
                releaseDate = "2022-11-09",
                title = "Black Panther: Wakanda ForeverBlack Panther: Wakanda Forever"
            )
            )
        }
    }
}