package id.ergun.klikdoa.data.repository

import id.ergun.klikdoa.data.model.Doa
import id.ergun.klikdoa.data.model.DoaData

/**
 * Created by alfacart on 12/12/22.
 */
class DoaRepository {
  fun getDoas(): List<Doa> {
    return DoaData.doas
  }

  fun searchDoa(query: String): List<Doa>{
    return DoaData.doas.filter {
      it.name.contains(query, ignoreCase = true)
    }
  }
}