package id.ergun.klikdoa.presentation.feature.doa

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.ergun.klikdoa.R
import id.ergun.klikdoa.data.model.Doa
import id.ergun.klikdoa.data.repository.DoaRepository
import id.ergun.klikdoa.presentation.ui.theme.KlikDoaTheme
import id.ergun.klikdoa.presentation.viewmodel.DoaViewModel
import id.ergun.klikdoa.presentation.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

/**
 * @author erikgunawan
 * Created 24/12/22 at 22.53
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DoaScreen(
  modifier: Modifier = Modifier,
  viewModel: DoaViewModel = viewModel(factory = ViewModelFactory(DoaRepository())),
  navigateToDetail: (String) -> Unit,
) {
  val groupedDoas by viewModel.groupedDoas.collectAsState()
  val query by viewModel.query

  Box(modifier = modifier) {
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val showButton: Boolean by remember {
      derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }
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
//        stickyHeader {
//          CharacterHeader(initial)
//        }
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

    AnimatedVisibility(
      visible = showButton,
      enter = fadeIn() + slideInVertically(),
      exit = fadeOut() + slideOutVertically(),
      modifier = Modifier
        .padding(bottom = 30.dp)
        .align(Alignment.BottomCenter)
    ) {
      ScrollToTopButton(
        onClick = {
          scope.launch {
            listState.animateScrollToItem(index = 0)
          }
        }
      )
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
fun ScrollToTopButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Button(
    onClick = onClick,
    modifier = modifier
      .shadow(10.dp, shape = CircleShape)
      .clip(shape = CircleShape)
      .size(56.dp),
    colors = ButtonDefaults.buttonColors(
//      backgroundColor = Color.White,
      contentColor = MaterialTheme.colors.primary
    )
  ) {
    Icon(
      imageVector = Icons.Filled.KeyboardArrowUp,
      contentDescription = stringResource(R.string.app_name),
    )
  }
}

@Composable
fun CharacterHeader(
  char: Char,
  modifier: Modifier = Modifier
) {
  Surface(
    color = MaterialTheme.colors.primary,
    modifier = modifier
  ) {
    Text(
      text = char.toString(),
      fontWeight = FontWeight.Black,
      color = Color.White,
      textAlign = TextAlign.Center,
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
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
      Text(stringResource(R.string.app_name))
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