package id.ergun.klikdoa.data.local.doa

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.ergun.klikdoa.common.Util.FAVORITE_DOA_TABLE_NAME
import id.ergun.klikdoa.data.model.Doa

/**
 * @author erikgunawan
 * Created 29/12/22 at 09.21
 */
@Entity(tableName = FAVORITE_DOA_TABLE_NAME)
data class DoaFavorite(
    @PrimaryKey val id: String,
    val doaName: String,
    val doaInArabic: String = "",
    val doaInLatin: String = "",
    val doaInBahasa: String = "",
    val footNote: String = "",
    val createdDate: Long
) {

    companion object {
        fun DoaFavorite.tranform(): Doa {
            return Doa(
                this.id,
                this.doaName,
                this.doaInArabic,
                this.doaInLatin,
                this.doaInBahasa,
                this.footNote
            )
        }

        fun Doa.transformToDoaFavoriteModel(): DoaFavorite {
            return DoaFavorite(
                this.id,
                this.doaName,
                this.doaInArabic,
                this.doaInLatin,
                this.doaInBahasa,
                this.footNote,
                System.currentTimeMillis()
            )
        }
    }
}