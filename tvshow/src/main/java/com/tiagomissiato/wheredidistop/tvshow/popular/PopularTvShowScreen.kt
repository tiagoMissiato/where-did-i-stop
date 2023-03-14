package com.tiagomissiato.wheredidistop.tvshow.popular

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tiagomissiato.wheredidistop.core.data.mock.popularTvShowList
import com.tiagomissiato.wheredidistop.core.model.dto.TvShow
import com.tiagomissiato.wheredidistop.core.ui.theme.WhereDidIStopTheme
import com.tiagomissiato.wheredidistop.ui.component.card.CardTvShowItem

@Composable
fun PopularTvShowList(
    modifier: Modifier = Modifier,
    viewModel: PopularTvShowListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    if(uiState.tvShowList.isEmpty()) {
        LoadTvShow { viewModel.getPopularList() }
    } else {
        PopularTvShowList(modifier, uiState.tvShowList)
    }
}

@Composable
fun PopularTvShowList(
    modifier: Modifier = Modifier,
    items: List<TvShow>,
    isLoading: Boolean = false
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { tvShow ->
            CardTvShowItem(tvShow = tvShow)
        }
        if (isLoading) {
            item {
                CardTvShowItem(tvShow = popularTvShowList(1)[0])
            }
        }
    }
}

@Composable
fun LoadTvShow(onClick: () -> Unit) {
    Button(
        onClick = { onClick() }
    ) {
        Text(text = "Load Tv Show")
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
            PopularTvShowList(Modifier, popularTvShowList(10))
        }
    }
}