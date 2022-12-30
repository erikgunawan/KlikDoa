package id.ergun.klikdoa.data.repository

import id.ergun.klikdoa.data.local.doa.DoaFavorite
import id.ergun.klikdoa.data.local.doa.DoaFavorite.Companion.transformToDoaFavoriteModel
import id.ergun.klikdoa.data.local.doa.DoaFavoriteDao
import id.ergun.klikdoa.data.model.Doa
import id.ergun.klikdoa.data.model.DoaData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author erikgunawan
 * Created 24/12/22 at 22.50
 */
class DoaRepository @Inject constructor(
    val doaFavoriteDao: DoaFavoriteDao
) {
    fun getDoas(): List<Doa> {
        return DoaData.doas
    }

    fun searchDoa(query: String): List<Doa>{
        return DoaData.doas.filter {
            it.doaName.contains(query, ignoreCase = true)
        }
    }

    fun getFavoriteDoas(): Flow<List<Doa>> {
        return doaFavoriteDao.getFavoriteDoas().map {
            DoaFavorite.tranform(it)
        }
    }

  fun addFavoriteDoa(doa: Doa) = doaFavoriteDao.addFavoriteDoa(
    doa.transformToDoaFavoriteModel()
  )

  fun removeFavoriteDoa(doaId: String) = doaFavoriteDao.removeFavoriteDoaById(doaId)
}