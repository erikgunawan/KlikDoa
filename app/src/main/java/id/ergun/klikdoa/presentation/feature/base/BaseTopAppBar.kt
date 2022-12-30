package id.ergun.klikdoa.presentation.feature.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import id.ergun.klikdoa.R.drawable
import id.ergun.klikdoa.common.Util.openTelegram

/**
 * @author erikgunawan
 * Created 29/12/22 at 16.04
 */
@Composable
fun BaseTopAppBar(
    navigateBack: () -> Unit,
    topAppBarAction: @Composable () -> Unit
) {
  Box(
    contentAlignment = Alignment.TopStart,
    modifier = Modifier.fillMaxWidth()) {
    TopAppBar(
      title = {},
      actions = {
        topAppBarAction()
//
      },
      navigationIcon = {
        IconButton(
          onClick = navigateBack
        ) {
          Icon(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = null,
          )
        }
      }
    )
    Image(
      painter = painterResource(drawable.img_logo_toolbar),
      contentDescription = null,
      modifier = Modifier
        .requiredSize(84.dp)
    )
  }
}