package id.ergun.klikdoa.data.local.doa

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.ergun.klikdoa.data.model.Doa

/**
 * Created by alfacart on 28/12/22.
 */
@Entity(tableName = "doa_favorite_table")
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
        this.footNote
      )
    }

    fun tranform(list: List<DoaFavorite>): List<Doa> {
      return list.map {
        it.tranform()
      }
    }
  }
}