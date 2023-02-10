package com.tiagomissiato.wheredidistop.core.domain.movie

import com.tiagomissiato.wheredidistop.core.model.dto.Movie
import com.tiagomissiato.wheredidistop.core.data.repository.MovieRepository
import com.tiagomissiato.wheredidistop.core.data.repository.TvShowRepository
import com.tiagomissiato.wheredidistop.core.model.dto.TvShow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface GetPopularTvShowListUseCase {
    fun getPopularTvShowList(): Flow<List<TvShow>>
}

class GetPopularTvShowListUseCaseImpl @Inject constructor(
    private val tvShowRepository: TvShowRepository
): GetPopularTvShowListUseCase {

    override fun getPopularTvShowList() = flow {
        emit(tvShowRepository.getPopularTvShowList())
    }
}