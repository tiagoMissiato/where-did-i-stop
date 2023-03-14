package com.tiagomissiato.wheredidistop.ui.component.card

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.tiagomissiato.wheredidistop.core.ui.theme.WhereDidIStopTheme
import com.tiagomissiato.wheredidistop.ui.component.R

@Composable
fun ErrorStateScreen(modifier:Modifier = Modifier, tryAgainClick: () -> Unit) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = modifier.align(Alignment.Center)) {
            Image(
                painter = painterResource(R.drawable.error_tv),
                contentDescription = "",
            )
            Button(
                onClick = { tryAgainClick() },
                modifier = modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Try again")
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun DefaultPreviewLoadMore() {

    WhereDidIStopTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ErrorStateScreen() {

            }
        }
    }
}