package id.ergun.klikdoa.data.repository

import id.ergun.klikdoa.data.model.Doa
import id.ergun.klikdoa.data.model.DoaData

/**
 * @author erikgunawan
 * Created 24/12/22 at 22.50
 */
class DoaRepository {
    fun getDoas(): List<Doa> {
        return DoaData.doas
    }

    fun searchDoa(query: String): List<Doa>{
        return DoaData.doas.filter {
            it.doaName.contains(query, ignoreCase = true)
        }
    }
}