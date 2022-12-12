package id.ergun.klikdoa.presentation.feature.doa

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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.ergun.klikdoa.R
import id.ergun.klikdoa.data.repository.DoaRepository
import id.ergun.klikdoa.presentation.ui.theme.KlikDoaTheme
import id.ergun.klikdoa.presentation.viewmodel.DoaViewModel
import id.ergun.klikdoa.presentation.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

/**
 * Created by alfacart on 12/12/22.
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DoaScreen(
  modifier: Modifier = Modifier,
  viewModel: DoaViewModel = viewModel(factory = ViewModelFactory(DoaRepository()))
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
          modifier = Modifier.background(MaterialTheme.colorScheme.primary)
        )
      }
      groupedDoas.forEach { (initial, doas) ->
//        stickyHeader {
//          CharacterHeader(initial)
//        }
        items(doas, key = { it.id }) { doa ->
          DoaListItem(
            name = doa.name,
            doaInArabic = doa.doaInArabic,
            photoUrl = "",
            modifier = Modifier
              .fillMaxWidth()
              .animateItemPlacement(tween(durationMillis = 100))
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
  name: String,
  doaInArabic: String,
  photoUrl: String,
  modifier: Modifier = Modifier
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier.clickable {}
  ) {
//    AsyncImage(
//      model = photoUrl,
//      contentDescription = null,
//      contentScale = ContentScale.Crop,
//      modifier = Modifier
//        .padding(8.dp)
//        .size(60.dp)
//        .clip(CircleShape)
//    )
    Text(
      text = name,
      fontWeight = FontWeight.Medium,
      modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp)
    )
    Text(
        text = doaInArabic,
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
      contentColor = MaterialTheme.colorScheme.primary
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
    color = MaterialTheme.colorScheme.primary,
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

@OptIn(ExperimentalMaterial3Api::class)
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
    DoaScreen()
  }
}

@Preview(showBackground = true)
@Composable
fun DoaListItemPreview() {
  KlikDoaTheme {
    DoaListItem(
      name = "H.O.S. Cokroaminoto",
      doaInArabic = "بِاسْمِكَ رَبِّيْ وَضَعْتُ جَنْبِيْ، وَبِكَ أَرْفَعُهُ، إِنْ أَمْسَكْتَ نَفْسِيْ فَارْحَمْهَا، وَإِنْ أَرْسَلْتَهَا فَاحْفَظْهَا بِمَا تَحْفَظُ بِهِ عِبَادَكَ الصَّالِحِيْنَ",
      photoUrl = ""
    )
  }
}