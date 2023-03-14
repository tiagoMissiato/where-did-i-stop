package com.tiagomissiato.wheredidistop.core.domain.usecase.tvshow

import com.tiagomissiato.wheredidistop.core.domain.repository.tvshow.TvShowRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularTvShowUseCase @Inject constructor(
    private val tvShowRepository: TvShowRepository
) {
    operator fun invoke() = flow {
        emit(tvShowRepository.getPopularTvShowList())
    }
}