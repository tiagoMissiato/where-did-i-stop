package com.tiagomissiato.wheredidistop.tvshow.popular.domain

import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularTvShowUseCase @Inject constructor(
    private val tvShowRepository: TvShowRepository
) {
    operator fun invoke() = flow {
        emit(tvShowRepository.getPopularTvShowList())
    }
}