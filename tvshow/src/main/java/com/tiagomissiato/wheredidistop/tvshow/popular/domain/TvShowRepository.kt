package com.tiagomissiato.wheredidistop.tvshow.popular.domain

import com.tiagomissiato.wheredidistop.tvshow.popular.data.model.TvShow

interface TvShowRepository {
    suspend fun getPopularTvShowList(): List<TvShow>
}
