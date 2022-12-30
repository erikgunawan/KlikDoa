package id.ergun.klikdoa.presentation.feature.doa.favorite

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.ergun.klikdoa.R
import id.ergun.klikdoa.presentation.feature.base.DoaListItem
import id.ergun.klikdoa.presentation.feature.base.EmptyView
import id.ergun.klikdoa.presentation.feature.base.SearchBar
import id.ergun.klikdoa.presentation.ui.theme.KlikDoaTheme
import id.ergun.klikdoa.presentation.viewmodel.DoaViewModel

/**
 * @author erikgunawan
 * Created 29/12/22 at 09.23
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DoaFavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: DoaViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit,
) {
//    LaunchedEffect(Unit) {
//        viewModel.getFavoriteDoas()
//    }
    val query by viewModel.query

    val favoriteDoas by viewModel.favoriteDoas.collectAsState(
      initial = emptyList()
    )

    if (favoriteDoas.isEmpty()) {
        EmptyView()
    } else {
        Box(modifier = modifier) {
            val listState = rememberLazyListState()
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                item {
                    SearchBar(
                        query = query,
                      placeholder = stringResource(R.string.search_favorite_doa),
                        onQueryChange = viewModel::search,
                        modifier = Modifier.background(MaterialTheme.colors.primary)
                    )
                }
                items(favoriteDoas) { doa ->
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
}


@Preview(showBackground = true)
@Composable
fun DoaScreenPreview() {
    KlikDoaTheme {
//        DoaScreen()
    }
}