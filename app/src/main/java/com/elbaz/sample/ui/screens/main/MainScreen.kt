@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)

package com.elbaz.sample.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.elbaz.sample.R
import com.elbaz.sample.data.models.AnimeModel
import com.elbaz.sample.ui.common.BottomNavBar
import com.elbaz.sample.ui.common.CardLarge
import com.elbaz.sample.ui.common.CardSmall
import com.elbaz.sample.ui.theme.PindjurRed
import com.elbaz.sample.ui.theme.WhiteEdgar
import com.elbaz.sample.ui.theme.grey
import com.elbaz.sample.ui.theme.spacing

@Preview(device = PIXEL_4, showSystemUi = true, showBackground = true, backgroundColor = 0xFF171717)
@Composable
fun MainScreenPreview() {

    //Remove this, it will cause leaks
    val viewModel  : MainScreenViewModel =  hiltViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(
        topBar = { TopBar(modifier = Modifier) },
        snackbarHost = { snackbarHostState }
    ) { innerpadding ->
        val pullRefreshState =
            rememberPullRefreshState(uiState.isLoading, { viewModel.onRefresh() })

LaunchedEffect(key1 = uiState.msg){
    snackbarHostState.showSnackbar(uiState.msg)
}
        Box(
            Modifier
                .background(Color(0xFF171717))
                .padding(innerpadding)
                .pullRefresh(pullRefreshState),
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())

            ) {
                Section(Modifier, uiState.popular, stringResource(R.string.most_popular)) {
                    CardLarge(it, uiState.isLoading)
                }

                Section(
                    modifier = Modifier, uiState.recent,
                    stringResource(R.string.recent_release)
                ) {
                    CardSmall(it,uiState.isLoading)
                }

                Section(
                    modifier = Modifier,
                    uiState.soon,
                    stringResource(R.string.coming_soon),
                ) { CardSmall(it,uiState.isLoading) }

                Spacer(modifier = Modifier.weight(1f))


            }
            BottomNavBar(
                Modifier
                    .padding(bottom = MaterialTheme.spacing.large)
                    .align(Alignment.BottomCenter)
            )
            PullRefreshIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                state = pullRefreshState,
                refreshing = uiState.isLoading
            )
        }
//        }
    }
}

@Composable
private fun Section(
    modifier: Modifier,
    data: List<AnimeModel>,
    title: String,
    item: @Composable (item: AnimeModel) -> Unit
) {
    //Should be called from a Column Scope
    Text(
        modifier = modifier.padding(start = 12.dp),
        text = title,
        color = WhiteEdgar,
        fontSize = 24.sp
    )
    Spacer(modifier = Modifier.height(8.dp))
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = MaterialTheme.spacing.large),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item { Spacer(modifier = modifier.width(MaterialTheme.spacing.large)) }
        items(data, { it.id }) {
            item(it)


        }
    }
}

@Composable
fun MainScreen() {
    Box(Modifier.fillMaxSize()) {
        BottomNavBar(Modifier.align(Alignment.BottomCenter))

    }


}

@Composable
fun TopBar(modifier: Modifier) {
    Row(
        modifier
            .height(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Filled.AccountCircle, contentDescription = "User Icon",
            modifier = Modifier
                .size(54.dp), tint = PindjurRed
        )
        Column(
            Modifier
                .fillMaxHeight()
                .padding(vertical = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(stringResource(R.string.good_morning), color = grey)

            Text(
                "Ameer Elbaz", color = PindjurRed, fontWeight = FontWeight(800), fontSize = dpToSp(
                    dp = 18.dp
                )
            )


        }
        Spacer(modifier = Modifier.weight(1f))

        Icon(
            modifier = Modifier.padding(4.dp),
            imageVector = Icons.Outlined.Search,
            contentDescription = "",
            tint = WhiteEdgar,
        )
        Icon(
            modifier = Modifier.padding(4.dp),
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "",
            tint = WhiteEdgar,
        )

    }
}

@Composable
fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }