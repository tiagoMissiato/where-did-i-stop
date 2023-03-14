package com.tiagomissiato.wheredidistop.core.testing

import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
open class BaseViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }
}