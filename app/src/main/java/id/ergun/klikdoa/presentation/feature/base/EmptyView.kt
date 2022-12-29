package id.ergun.klikdoa.presentation.feature.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.ergun.klikdoa.R

/**
 * @author erikgunawan
 * Created 29/12/22 at 23.29
 */
@Composable
fun EmptyView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.img_logo),
                contentDescription = null,
                modifier = Modifier
                    .requiredSize(84.dp)
            )
            Text(
                text = stringResource(id = R.string.empty_favorite_doa_message),
                fontWeight = FontWeight.Medium
            )
        }
    }
}