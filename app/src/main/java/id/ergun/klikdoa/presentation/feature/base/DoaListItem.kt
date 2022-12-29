package id.ergun.klikdoa.presentation.feature.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.ergun.klikdoa.data.model.Doa
import id.ergun.klikdoa.presentation.ui.theme.KlikDoaTheme

/**
 * @author erikgunawan
 * Created 29/12/22 at 23.09
 */
@Composable
fun DoaListItem(
    doa: Doa,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable {
                navigateToDetail(doa.id)
            }
            .padding(16.dp)
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