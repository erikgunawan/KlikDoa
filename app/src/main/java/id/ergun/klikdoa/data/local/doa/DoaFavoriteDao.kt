package id.ergun.klikdoa.data.local.doa

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import id.ergun.klikdoa.common.Util.FAVORITE_DOA_TABLE_NAME
import kotlinx.coroutines.flow.Flow

/**
 * @author erikgunawan
 * Created 29/12/22 at 09.20
 */
@Dao
interface DoaFavoriteDao {
    @Query("SELECT * FROM $FAVORITE_DOA_TABLE_NAME ORDER BY id ASC")
    fun getFavoriteDoas(): Flow<List<DoaFavorite>>

    @Insert
    fun addFavoriteDoa(doaFavorite: DoaFavorite)

    @Delete
    fun deleteFavoriteDoa(doaFavorite: DoaFavorite)
}