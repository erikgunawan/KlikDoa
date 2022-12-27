package id.ergun.klikdoa.presentation.feature.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import id.ergun.klikdoa.presentation.ui.theme.KlikDoaTheme

/**
 * @author erikgunawan
 * Created 26/12/22 at 11.17
 */

class SplashActivity : ComponentActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      KlikDoaTheme {
        // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colors.background
        ) {
          SplashScreen()
//                    Greeting("Android")
        }
      }


    }
  }
}
