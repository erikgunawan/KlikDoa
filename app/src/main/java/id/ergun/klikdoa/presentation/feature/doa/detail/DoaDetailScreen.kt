package id.ergun.klikdoa.presentation.feature.doa.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import id.ergun.klikdoa.R
import id.ergun.klikdoa.common.Util.showToast
import id.ergun.klikdoa.data.model.Doa
import id.ergun.klikdoa.presentation.feature.base.BaseTopAppBar
import id.ergun.klikdoa.presentation.ui.theme.GreenPrimary
import id.ergun.klikdoa.presentation.ui.theme.GreenSecondary
import id.ergun.klikdoa.presentation.viewmodel.DoaViewModel

/**
 * @author erikgunawan
 * Created 24/12/22 at 23.44
 */
@Composable
fun DoaDetailScreen(
    doaId: String,
    viewModel: DoaViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getFavoriteDoaById(doaId)
    }
    DetailContent(viewModel.getDoaById(doaId),
        updateFavoriteDoa = {
            if (viewModel.favoriteDoa.id != "0") {
                viewModel.removeFavoriteDoa(doaId)
            } else {
                viewModel.addFavoriteDoa(viewModel.getDoaById(doaId))
            }
            showToast(context, context.getString(if (viewModel.favoriteDoa.id != "0") R.string.remove_from_favorite_doa_message else R.string.add_to_favorite_doa_message))
            navigateBack()
        },
        fabIcon = if (viewModel.favoriteDoa.id != "0")
            R.drawable.ic_favorite_checked
        else R.drawable.ic_favorite,
        navigateBack = navigateBack)
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun DetailContent(
    doa: Doa,
    @DrawableRes fabIcon: Int = R.drawable.ic_favorite,
    updateFavoriteDoa: () -> Unit,
    navigateBack: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            BaseTopAppBar(
                navigateBack = {
                    navigateBack()
                }
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Text(
                    text = doa.doaName,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    color = GreenPrimary,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp)
                )

                Spacer(modifier = Modifier.padding(top = 16.dp))

                Text(
                    text = doa.doaInArabic,
                    fontSize = TextUnit(24F, TextUnitType.Sp),
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp)
                )

                Spacer(modifier = Modifier.padding(top = 16.dp))

                Text(
                    text = doa.doaInLatin,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp)
                )

                Spacer(modifier = Modifier.padding(top = 24.dp))

                Text(
                    text = stringResource(id = R.string.meaning),
                    fontWeight = FontWeight.Medium,
                    color = GreenSecondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp)
                )

                Text(
                    text = doa.doaInBahasa,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp)
                )

                Spacer(modifier = Modifier.padding(top = 24.dp))

                Text(
                    text = stringResource(id = R.string.foot_note),
                    fontWeight = FontWeight.Medium,
                    color = GreenSecondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp)
                )

                Text(
                    text = doa.footNote,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp)
                )

                Spacer(modifier = Modifier.padding(top = 48.dp))
            }

        }

        FloatingActionButton(
            backgroundColor = GreenSecondary,
            modifier = Modifier
                .padding(all = 16.dp)
                .align(alignment = Alignment.BottomEnd),
            onClick = {
                updateFavoriteDoa()
            }) {
            Icon(imageVector = ImageVector.vectorResource(id = fabIcon),
                contentDescription = "Update Favorite")
        }
    }
}