package id.ergun.klikdoa.presentation.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
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
import id.ergun.klikdoa.common.Util.openTelegram
import id.ergun.klikdoa.presentation.feature.base.BaseTopAppBar

/**
 * @author erikgunawan
 * Created 29/12/22 at 09.25
 */
@Composable
fun ProfileScreen(
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            BaseTopAppBar(navigateBack = {
                navigateBack()
            }) {
            }
        },
        content = { padding ->
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {

                    Text(
                        text = stringResource(R.string.my_name),
                        fontSize = 20.sp
                    )

                  Spacer(modifier = Modifier.padding(top = 24.dp))

                    Image(
                        painter = painterResource(R.drawable.img_e),
                        contentDescription = null,
                        modifier = Modifier
                          .requiredSize(128.dp)
                          .clip(CircleShape)
                    )

                  Spacer(modifier = Modifier.padding(top = 24.dp))

                    Text(
                        text = stringResource(R.string.my_description),
                        fontSize = 18.sp
                    )

                  Spacer(modifier = Modifier.padding(top = 48.dp))

                    Text(
                        text = stringResource(R.string.find_me_on),
                        fontSize = 16.sp
                    )
                    Row(
                        modifier = Modifier
                          .fillMaxWidth()
                          .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Image(
                            painter = painterResource(R.drawable.ic_linkedin),
                            contentDescription = null,
                            modifier = Modifier
                              .clickable {
                                context.openLinkedIn()
                              }
                              .requiredSize(60.dp)
                        )
                        Image(
                            painter = painterResource(R.drawable.ic_github),
                            contentDescription = null,
                            modifier = Modifier
                              .clickable {
                                context.openGithub()
                              }
                              .padding(8.dp)
                              .requiredSize(60.dp)
                        )
                        Image(
                            painter = painterResource(R.drawable.ic_instagram),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {
                                    context.openInstagram()
                                }
                                .requiredSize(60.dp)
                        )
                        Image(
                            painter = painterResource(R.drawable.ic_telegram),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {
                                    context.openTelegram()
                                }
                                .requiredSize(60.dp)
                        )
                    }
                }
            }
        }
    )
}