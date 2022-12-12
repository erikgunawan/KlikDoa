package id.ergun.klikdoa.data.model

/**
 * Created by alfacart on 12/12/22.
 */
data class Doa(
  val id: String,
  val name: String,
  val doaInArabic: String = "",
  val doaInLatin: String = "",
  val doaInBahasa: String = "",
  val footNote: String = ""
)