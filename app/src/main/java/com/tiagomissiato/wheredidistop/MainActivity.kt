package com.tiagomissiato.wheredidistop

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.tiagomissiato.wheredidistop.core.network.api.TmdbApiService
import com.tiagomissiato.wheredidistop.core.ui.theme.WhereDidIStopTheme
import com.tiagomissiato.wheredidistop.data.movie.model.MovieDTO
import com.tiagomissiato.wheredidistop.movie.popular.PopularMovieListViewModel
import com.tiagomissiato.wheredidistop.ui.theme.Typography
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var apiService: TmdbApiService

    private val exampleViewModel: PopularMovieListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            WhereDidIStopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        Log.i("Debug", apiService.toString())

    }
}

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    viewModel: PopularMovieListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    if(uiState.movieList.isEmpty()) {
        LoadMovies { viewModel.getPopularList() }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.movieList) { movie ->
                MovieListItem(movie)
            }
        }
    }
}

@Composable
fun LoadMovies(onClick: () -> Unit) {
    Button(
        onClick = { onClick() }
    ) {
        Text(text = "Load movie")
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieListItem(movie: MovieDTO) {
    Card {
        Row(modifier = Modifier.padding(horizontal = 4.dp)) {
            GlideImage(
                modifier = Modifier.height(150.dp),
                model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                contentDescription = "description"
            )
            Column(modifier = Modifier.padding(start = 4.dp)) {
                Text(text = movie.title)
                Text(text = movie.overview, style = Typography.labelSmall)
            }
        }
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
            LoadMovies {

            }
//            MovieListItem(MovieDTO(
//                id = 1,
//                adult = false,
//                originalLanguage = "en",
//                originalTitle = "Black Panther: Wakanda Forever",
//                overview = "Queen Ramonda, Shuri, M’Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T’Challa’s death.  As the Wakandans strive to embrace their next chapter, the heroes must band together with the help of War Dog Nakia and Everett Ross and forge a new path for the kingdom of Wakanda.",
//                posterPath = "/sv1xJUazXeYqALzczSZ3O6nkH75.jpg",
//                releaseDate = "2022-11-09",
//                title = "Black Panther: Wakanda Forever"
//            ))
        }
    }
}