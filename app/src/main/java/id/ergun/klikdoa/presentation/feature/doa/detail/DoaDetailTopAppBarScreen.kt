package id.ergun.klikdoa.presentation.feature.doa.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import id.ergun.klikdoa.R.drawable

/**
 * Created by alfacart on 28/12/22.
 */

@Composable
fun DoaDetailTopAppBarScreen(
  navigateBack: () -> Unit,
  topAppBarAction: @Composable () -> Unit
) {
  TopAppBar (
    title = {
      Text(
        text = "Test"
      )
    },actions = {
                topAppBarAction()
//
    },
    navigationIcon = {
      IconButton(
        onClick = navigateBack
      ) {
        Icon(
          imageVector = Outlined.ArrowBack,
          contentDescription = null,
        )
      }
    }
  )
}