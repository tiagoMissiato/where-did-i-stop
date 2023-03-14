package com.tiagomissiato.wheredidistop.movie

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tiagomissiato.wheredidistop.movie.popular.PopularMovieList
import com.tiagomissiato.wheredidistop.movie.popular.PopularMovieUiState

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun useAppContext() {
        composeTestRule.setContent {
            PopularMovieList(
                uiState = PopularMovieUiState.Empty,
                onRefresh = {},
                onTryAgain = {}
            )
        }

        composeTestRule.onNodeWithText("Try again").assertExists()
    }
}