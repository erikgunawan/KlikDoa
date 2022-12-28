package id.ergun.klikdoa.presentation.feature.doa.favorite

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.ergun.klikdoa.R.string
import id.ergun.klikdoa.data.model.Doa
import id.ergun.klikdoa.presentation.feature.doa.ScrollToTopButton
import id.ergun.klikdoa.presentation.ui.theme.KlikDoaTheme
import id.ergun.klikdoa.presentation.viewmodel.DoaViewModel
import kotlinx.coroutines.launch

/**
 * Created by alfacart on 28/12/22.
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DoaFavoriteScreen(
  modifier: Modifier = Modifier,
  viewModel: DoaViewModel = hiltViewModel(),
  navigateToDetail: (String) -> Unit,
) {
  LaunchedEffect(Unit) {
    viewModel.getFavoriteDoas()
  }
  val groupedDoas by viewModel.groupedDoas.collectAsState()
  val query by viewModel.query

  Box(modifier = modifier) {
    val listState = rememberLazyListState()
    LazyColumn(
      state = listState,
      contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        item {
          SearchBar(
            query = query,
            onQueryChange = viewModel::search,
            modifier = Modifier.background(MaterialTheme.colors.primary)
          )
        }
      groupedDoas.forEach { (_, doas) ->

        items(doas, key = { it.id }) { doa ->
          DoaListItem(
            doa = doa,
            modifier = Modifier
              .fillMaxWidth()
              .animateItemPlacement(tween(durationMillis = 100)),
            navigateToDetail = navigateToDetail
          )
        }
      }
    }
  }
}

@Composable
fun DoaListItem(
  doa: Doa,
  modifier: Modifier = Modifier,
  navigateToDetail: (String) -> Unit,
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier.clickable {
      navigateToDetail(doa.id)
    }.padding(16.dp)
  ) {
    Text(
      text = doa.doaName,
      fontWeight = FontWeight.Medium,
      modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp)
    )
  }
}

@Composable
fun SearchBar(
  query: String,
  onQueryChange: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  TextField(
    value = query,
    onValueChange = onQueryChange,
    leadingIcon = {
      Icon(
        imageVector = Icons.Default.Search,
        contentDescription = null
      )
    },
    colors = TextFieldDefaults.textFieldColors(
//      backgroundColor = MaterialTheme.colors.surface,
      disabledIndicatorColor = Color.Transparent,
      focusedIndicatorColor = Color.Transparent,
      unfocusedIndicatorColor = Color.Transparent,
    ),
    placeholder = {
      Text(stringResource(string.app_name))
    },
    modifier = modifier
      .padding(16.dp)
      .fillMaxWidth()
      .heightIn(min = 48.dp)
      .clip(RoundedCornerShape(16.dp))
  )
}

@Preview(showBackground = true)
@Composable
fun DoaScreenPreview() {
  KlikDoaTheme {
//        DoaScreen()
  }
}

@Preview(showBackground = true)
@Composable
fun DoaListItemPreview() {
  KlikDoaTheme {
    DoaListItem(
      doa = Doa(
        "1",
        "Doa Sebelum Tidur 1",
        "بِاسْمِكَ رَبِّيْ وَضَعْتُ جَنْبِيْ، وَبِكَ أَرْفَعُهُ، إِنْ أَمْسَكْتَ نَفْسِيْ فَارْحَمْهَا، وَإِنْ أَرْسَلْتَهَا فَاحْفَظْهَا بِمَا تَحْفَظُ بِهِ عِبَادَكَ الصَّالِحِيْنَ",
        "Bismika robbii wa dho'tu janbii, wa bika arfa'uhu, in amsakta nafsii farhamhaa, wa in arsaltahaa fahfazhhaa bimaa tahfazhu bihi 'ibaadakash-sholihiin.",
        "Artinya: \n" +
            "\n" +
            "Dengan nama Engkau, wahai Tuhanku, aku meletakkan lambungku. Dan dengan namaMu pula aku bangun daripadanya. Apabila Engkau menahan rohku (mati), maka berilah rahmat padanya. Tapi apabila Engkau melepaskannya, maka peliharalah, sebagaimana Engkau memelihara hamba-hambaMu yang shalih.",
        "Tentang Doa: \n" +
            "\n" +
            "HR. Al-Bukhari 11/126, Muslim 4/2084.\n" +
            "\"Apabila seseorang di antara kalian bangkit dari tempat tidurnya kemudian ingin kembali lagi, hendaknya ia mengibaskan ujung kainnya 3x, dan menyebut nama Allah, karena ia tidak tahu apa yang ditinggalkannya di atas tempat tidur setelah ia bangkit. Apabila ia ingin berbaring, maka hendaknya ia membaca: (doa di atas).\"\n" +
            "\n" +
            "\n" +
            "Sumber: Hisnul Muslim."
      ),
      navigateToDetail = {

      }
    )
  }
}