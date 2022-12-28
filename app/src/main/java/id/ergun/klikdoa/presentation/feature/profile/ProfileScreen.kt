package id.ergun.klikdoa.presentation.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ergun.klikdoa.R
import id.ergun.klikdoa.common.Util.openGithub
import id.ergun.klikdoa.common.Util.openInstagram
import id.ergun.klikdoa.common.Util.openLinkedIn

/**
 * Created by alfacart on 28/12/22.
 */
@Composable
fun ProfileScreen() {
  val context = LocalContext.current
  Column(
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "Hi, I'm Erik",
      fontSize = 18.sp,
      modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp)
    )
    Image(
      painter = painterResource(R.drawable.ic_favorite),
      contentDescription = null,
      modifier = Modifier
        .requiredSize(60.dp)
        .clip(CircleShape)
    )
    Text(
      text = stringResource(R.string.app_name),
      fontSize = 10.sp,
      modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp)
    )
    Row(
      modifier = Modifier.fillMaxWidth().padding(12.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {

      Image(
        painter = painterResource(R.drawable.ic_favorite),
        contentDescription = null,
        modifier = Modifier.clickable {
          context.openLinkedIn()
        }
          .requiredSize(60.dp)
          .clip(CircleShape)
      )
      Image(
        painter = painterResource(R.drawable.ic_favorite),
        contentDescription = null,
        modifier = Modifier.clickable {
          context.openGithub()
        }
          .requiredSize(60.dp)
          .clip(CircleShape)
      )
      Image(
        painter = painterResource(R.drawable.ic_favorite),
        contentDescription = null,
        modifier = Modifier.clickable {
          context.openInstagram()
        }
          .requiredSize(60.dp)
          .clip(CircleShape)
      )
    }
  }
}