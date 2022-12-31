package id.ergun.klikdoa.data.local.doa

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import id.ergun.klikdoa.common.Util.FAVORITE_DOA_TABLE_NAME

/**
 * @author erikgunawan
 * Created 29/12/22 at 09.20
 */
@Dao
interface DoaFavoriteDao {
    @Query("SELECT * FROM $FAVORITE_DOA_TABLE_NAME ORDER BY createdDate ASC")
    fun getFavoriteDoas(): List<DoaFavorite>

    @Query("SELECT * FROM $FAVORITE_DOA_TABLE_NAME WHERE doaName LIKE  '%' || :doaName || '%' ORDER BY createdDate ASC")
    fun searchFavoriteDoas(doaName: String): List<DoaFavorite>

    @Query("SELECT * FROM $FAVORITE_DOA_TABLE_NAME WHERE id = :id")
    fun getFavoriteDoaById(id: String): DoaFavorite?

    @Insert(onConflict = IGNORE)
    fun addFavoriteDoa(doaFavorite: DoaFavorite)

    @Query("DELETE FROM $FAVORITE_DOA_TABLE_NAME WHERE id = :id")
    fun removeFavoriteDoaById(id: String)
}