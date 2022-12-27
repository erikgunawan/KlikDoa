package id.ergun.klikdoa.presentation.feature.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.ergun.klikdoa.R
import id.ergun.klikdoa.data.model.Doa
import id.ergun.klikdoa.presentation.feature.doa.DoaScreen
import id.ergun.klikdoa.presentation.feature.doa.detail.DetailScreen
import id.ergun.klikdoa.presentation.ui.navigation.Screen
import kotlinx.coroutines.delay

/**
 * @author erikgunawan
 * Created 26/12/22 at 11.17
 */
@Composable
fun SplashScreen(navController: NavController) {
  val scale = remember {
    androidx.compose.animation.core.Animatable(0f)
  }

  // AnimationEffect
  LaunchedEffect(key1 = true) {
    scale.animateTo(
      targetValue = 0.7f,
      animationSpec = tween(
        durationMillis = 800,
        easing = {
          OvershootInterpolator(4f).getInterpolation(it)
        })
    )
    delay(3000L)
    navController.navigate(Screen.Home.route)
  }

  // Image
  Box(contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()) {
    Image(painter = painterResource(id = R.drawable.ic_launcher_background),
      contentDescription = "Logo",
      modifier = Modifier.scale(scale.value))
  }
}