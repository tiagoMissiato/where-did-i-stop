package com.tiagomissiato.wheredidistop.tvshow.popular.data

import com.tiagomissiato.wheredidistop.tvshow.popular.data.model.TvShow
import kotlin.random.Random
import kotlin.random.nextInt

fun popularTvShowList(numItems: Int) = List(numItems) {
   TvShow(
        id = it.toLong(),
        adult = false,
        voteAverage = Random.nextInt(0..10).toDouble(),
        originalLanguage = listOf("en", "pt", "eu").shuffled()[0],
        overview = "Queen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’Baku,",
        posterPath = "/sv1xJUazXeYqALzczSZ3O6nkH75.jpg",
        releaseDate = "2022-11-09",
        title = "Black Panther: Wakanda ForeverBlack Panther: Wakanda Forever"
    )
}