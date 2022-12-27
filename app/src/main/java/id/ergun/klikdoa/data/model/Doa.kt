package id.ergun.klikdoa.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author erikgunawan
 * Created 24/12/22 at 22.47
 */
@Parcelize
data class Doa(
    val id: String,
    val doaName: String,
    val doaInArabic: String = "",
    val doaInLatin: String = "",
    val doaInBahasa: String = "",
    val footNote: String = ""
): Parcelable {

    companion object {
        fun generateDefaultDoa(): Doa {
            return Doa(
                "0",
                "",
            )
        }
    }
}