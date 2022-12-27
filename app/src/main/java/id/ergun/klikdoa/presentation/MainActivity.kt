package id.ergun.klikdoa.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import id.ergun.klikdoa.presentation.feature.main.MainScreen
import id.ergun.klikdoa.presentation.ui.theme.KlikDoaTheme

/**
 * @author erikgunawan
 * Created 10/12/22
 */
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      KlikDoaTheme {
        // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colors.background
        ) {
          MainScreen()
//                    Greeting("Android")
        }
      }
    }
  }

  companion object {
    fun newIntent(context: Context): Intent =
      Intent(context, MainActivity::class.java)
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  KlikDoaTheme {
    Greeting("Android")
  }
}