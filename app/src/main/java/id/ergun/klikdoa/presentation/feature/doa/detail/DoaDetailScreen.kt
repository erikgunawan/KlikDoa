package id.ergun.klikdoa.presentation.feature.doa.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.ergun.klikdoa.presentation.ui.theme.KlikDoaTheme

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

@Composable
fun DetailContent(
    title: String,
    basePoint: Int,
    count: Int,
    onBackClick: () -> Unit,
    onAddToCart: (count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    var totalPoint by rememberSaveable { mutableStateOf(0) }
    var orderCount by rememberSaveable { mutableStateOf(count) }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",//stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
            }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(Color.LightGray))
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            ProductCounter(
//                1,
//                orderCount,
//                onProductIncreased = { orderCount++ },
//                onProductDecreased = { if (orderCount > 0) orderCount-- },
//                modifier = Modifier
//                    .align(Alignment.CenterHorizontally)
//                    .padding(bottom = 16.dp)
//            )
//            totalPoint = basePoint * orderCount
//            OrderButton(
//                text = stringResource(R.string.add_to_cart, totalPoint),
//                enabled = orderCount > 0,
//                onClick = {
//                    onAddToCart(orderCount)
//                }
//            )
//        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    KlikDoaTheme {
        DetailContent(
            "Jaket Hoodie Dicoding",
            7500,
            1,
            onBackClick = {},
            onAddToCart = {}
        )
    }
}