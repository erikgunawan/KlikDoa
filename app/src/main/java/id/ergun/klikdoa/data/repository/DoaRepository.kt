package id.ergun.klikdoa.data.repository

import id.ergun.klikdoa.data.local.doa.DoaFavorite.Companion.tranform
import id.ergun.klikdoa.data.local.doa.DoaFavorite.Companion.transformToDoaFavoriteModel
import id.ergun.klikdoa.data.local.doa.DoaFavoriteDao
import id.ergun.klikdoa.data.model.Doa
import id.ergun.klikdoa.data.model.DoaData
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

    fun searchDoa(query: String): List<Doa> {
        return DoaData.doas.filter {
            it.doaName.contains(query, ignoreCase = true)
        }
    }

    fun getFavoriteDoas(): List<Doa> {
        return doaFavoriteDao.getFavoriteDoas().map {
            it.tranform()
        }
    }

    fun searchFavoriteDoa(doaName: String): List<Doa>{
        return doaFavoriteDao.searchFavoriteDoas(doaName).map {
            it.tranform()
        }
    }

    fun getFavoriteDoaById(doaId: String): Doa? {
        return doaFavoriteDao.getFavoriteDoaById(doaId)?.tranform()
    }

    fun addFavoriteDoa(doa: Doa) = doaFavoriteDao.addFavoriteDoa(
        doa.transformToDoaFavoriteModel()
    )

    fun removeFavoriteDoa(doaId: String) = doaFavoriteDao.removeFavoriteDoaById(doaId)
}