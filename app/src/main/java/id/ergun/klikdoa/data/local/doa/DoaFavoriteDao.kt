package id.ergun.klikdoa.data.local.doa

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Created by alfacart on 28/12/22.
 */

@Dao
interface DoaFavoriteDao {
  @Query("SELECT * FROM doa_favorite_table ORDER BY id ASC")
  fun getFavoriteDoas(): Flow<List<DoaFavorite>>

  @Insert()
  fun addBook(doaFavorite: DoaFavorite)

  @Update
  fun updateBook(doaFavorite: DoaFavorite)

  @Delete
  fun deleteBook(doaFavorite: DoaFavorite)
}