package com.tiagomissiato.wheredidistop.movie.data.mock

import com.tiagomissiato.wheredidistop.movie.data.model.Movie
import kotlin.random.Random
import kotlin.random.nextInt

fun popularMovieList(numItems: Int) = List(numItems) {
    Movie(
        id = it.toLong(),
        adult = false,
        voteAverage = Random.nextInt(0..10).toDouble(),
        originalLanguage = listOf("en", "pt", "eu").shuffled()[0],
        originalTitle = "Black Panther: Wakanda Forever",
        overview = "Queen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’Baku,",
        posterPath = "/sv1xJUazXeYqALzczSZ3O6nkH75.jpg",
        releaseDate = "2022-11-09",
        title = "Black Panther: Wakanda ForeverBlack Panther: Wakanda Forever"
    )
}