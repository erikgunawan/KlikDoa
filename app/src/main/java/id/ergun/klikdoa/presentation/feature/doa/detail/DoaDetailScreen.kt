package id.ergun.klikdoa.presentation.feature.doa.detail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import id.ergun.klikdoa.data.model.Doa
import id.ergun.klikdoa.data.model.DoaData
import id.ergun.klikdoa.presentation.ui.navigation.Screen.DetailDoa
import id.ergun.klikdoa.presentation.ui.theme.KlikDoaTheme
import id.ergun.klikdoa.R

/**
 * @author erikgunawan
 * Created 24/12/22 at 23.44
 */

@Composable
fun DetailScreen(
  doaId: String,
//    viewModel: DetailRewardViewModel = viewModel(
//        factory = ViewModelFactory(
//            Injection.provideRepository()
//        )
//    ),
  navigateBack: () -> Unit,
  navigateToCart: () -> Unit
) {
  val doa = DoaData.doas.find { it.id == "1" } ?: Doa.generateDefaultDoa()
  println("Doa detail " + doa.toString())
  DetailContent(doa)
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
  modifier: Modifier = Modifier,
) {
  var thumbIconLiked by remember {
    mutableStateOf(false)
  }
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    TopAppBar(
      title = { Text(text = "AppBar") },
      actions = {
        IconButton(onClick = {
          thumbIconLiked = !thumbIconLiked
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
      modifier = Modifier.padding(16.dp)
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