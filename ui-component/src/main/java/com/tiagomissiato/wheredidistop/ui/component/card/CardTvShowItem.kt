package com.tiagomissiato.wheredidistop.ui.component.card

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tiagomissiato.wheredidistop.core.ui.theme.WhereDidIStopTheme
import com.tiagomissiato.wheredidistop.ui.component.R
import com.tiagomissiato.wheredidistop.ui.component.card.vo.CardTvShowItemVo
import com.tiagomissiato.wheredidistop.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardTvShowItem(tvShow: CardTvShowItemVo)  {

    Box(modifier = Modifier.clickable { { } }, contentAlignment = Alignment.BottomStart) {
        Card(modifier = Modifier.padding(8.dp), elevation = CardDefaults.cardElevation(4.dp)) {
            Column(modifier = Modifier.padding(start = 100.dp, top = 8.dp, bottom = 8.dp, end = 8.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = tvShow.title,
                            style = Typography.titleSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row {
                            ElevatedAssistChip(
                                onClick = { { } },
                                label = {
                                    Text(text = tvShow.releaseDate, style = Typography.labelSmall,)
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.DateRange,
                                        contentDescription = "Localized description",
                                        modifier = Modifier.size(AssistChipDefaults.IconSize),
                                        tint = MaterialTheme.colorScheme.surfaceTint
                                    )
                                }
                            )
                            Divider(modifier = Modifier.width(8.dp))
                            ElevatedAssistChip(
                                onClick = { { } },
                                label = {
                                    Text(text = tvShow.originalLanguage, style = Typography.labelSmall,)
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Language,
                                        contentDescription = "Localized description",
                                        modifier = Modifier.size(AssistChipDefaults.IconSize),
                                        tint = MaterialTheme.colorScheme.surfaceTint
                                    )
                                }
                            )
                        }
                    }
                    Text(
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .padding(start = 4.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        text = tvShow.voteAverage.toString(),
                        style = Typography.titleMedium,
                    )
                }
            }
        }
        AsyncImage(
            modifier = Modifier
                .height(130.dp)
                .padding(start = 24.dp)
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = "https://image.tmdb.org/t/p/w500${tvShow.posterPath}",
            contentDescription = "description",
            placeholder = painterResource(R.drawable.mad_max_poster)
        )

    }
}

@Preview(showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewCardTvShowItem() {
    WhereDidIStopTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            CardTvShowItem(
                CardTvShowItemVo(
                    id = 1,
                    adult = false,
                    voteAverage = 5.6,
                    originalLanguage = "en",
                    overview = "Queen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’BakuQueen Ramonda, Shuri, M’Baku,",
                    posterPath = "/sv1xJUazXeYqALzczSZ3O6nkH75.jpg",
                    releaseDate = "2022-11-09",
                    title = "Black Panther: Wakanda ForeverBlack Panther: Wakanda Forever"
                )
            )
        }
    }
}