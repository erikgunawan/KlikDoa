package id.ergun.klikdoa.presentation.feature.doa

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.ergun.klikdoa.presentation.feature.base.DoaListItem
import id.ergun.klikdoa.presentation.feature.base.SearchBar
import id.ergun.klikdoa.presentation.viewmodel.DoaViewModel

/**
 * @author erikgunawan
 * Created 24/12/22 at 22.53
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DoaScreen(
    modifier: Modifier = Modifier,
    viewModel: DoaViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit,
) {
    val doaList by viewModel.doaList.collectAsState()
    val doaNameQuery by viewModel.doaName

    Box(modifier = modifier) {
        val listState = rememberLazyListState()
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                SearchBar(
                    query = doaNameQuery,
                    onQueryChange = viewModel::searchDoa,
                    modifier = Modifier.background(MaterialTheme.colors.primary)
                )
            }

            items(doaList) { doa ->
                DoaListItem(
                    doa = doa,
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItemPlacement(tween(durationMillis = 100)),
                    navigateToDetail = navigateToDetail
                )
            }
        }

    }
}