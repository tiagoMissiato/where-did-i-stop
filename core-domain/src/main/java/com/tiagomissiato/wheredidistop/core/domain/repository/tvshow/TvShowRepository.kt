package com.tiagomissiato.wheredidistop.core.domain.repository.tvshow

import com.tiagomissiato.wheredidistop.core.model.dto.TvShow

interface TvShowRepository {
    suspend fun getPopularTvShowList(): List<TvShow>
}
