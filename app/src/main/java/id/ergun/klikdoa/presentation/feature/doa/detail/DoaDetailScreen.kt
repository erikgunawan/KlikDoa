package id.ergun.klikdoa.presentation.feature.doa.detail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.ergun.klikdoa.R
import id.ergun.klikdoa.data.model.Doa
import id.ergun.klikdoa.presentation.ui.theme.KlikDoaTheme
import id.ergun.klikdoa.presentation.viewmodel.DoaViewModel

/**
 * @author erikgunawan
 * Created 24/12/22 at 23.44
 */

@Composable
fun DetailScreen(
    doaId: String,
    viewModel: DoaViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    DetailContent(viewModel.getDoaById(doaId), navigateBack = navigateBack)
//    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
//        when (uiState) {
//            is UiState.Loading -> {
//                viewModel.getRewardById(rewardId)
//            }
//            is UiState.Success -> {
//                val data = uiState.data
//                DetailContent(
//                    data.reward.image,
//                    data.reward.title,
//                    data.reward.requiredPoint,
//                    data.count,
//                    onBackClick = navigateBack,
//                    onAddToCart = { count ->
//                        viewModel.addToCart(data.reward, count)
//                        navigateToCart()
//                    }
//                )
//            }
//            is UiState.Error -> {}
//        }
//    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun DetailContent(
    doa: Doa,
    navigateBack: () -> Unit,
) {
    val mContext = LocalContext.current
    var thumbIconLiked by remember {
        mutableStateOf(false)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DoaDetailTopAppBarScreen(
            navigateBack = {
                navigateBack()
            },
            topAppBarAction = {
                IconButton(onClick = {
                    thumbIconLiked = !thumbIconLiked
                    Toast.makeText(mContext, thumbIconLiked.toString(), Toast.LENGTH_SHORT).show()
                }) {
                    Image(painter = painterResource(id = if (thumbIconLiked) {
                        R.drawable.ic_favorite_checked
                    } else {
                        R.drawable.ic_favorite
                    }),
                        contentDescription = "Logo",)
                }
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
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp)
            )
            Text(
                text = doa.doaInArabic,
                fontSize = TextUnit(24F, TextUnitType.Sp),
                fontWeight = FontWeight.Medium,
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

            Text(
                text = doa.doaInLatin,
                fontWeight = FontWeight.Medium,
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
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    KlikDoaTheme {
//        DetailContent(
//            "Jaket Hoodie Dicoding",
//            7500,
//            1,
//            onBackClick = {},
//            onAddToCart = {}
//        )
    }
}