package id.ergun.klikdoa.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ergun.klikdoa.data.local.doa.DoaFavorite
import id.ergun.klikdoa.data.local.doa.DoaFavoriteDao

/**
 * Created by alfacart on 28/12/22.
 */
@Database(entities = [DoaFavorite::class], version = 1, exportSchema = false)
abstract class KlikDoaDB : RoomDatabase() {
  abstract fun favoriteDoaDao(): DoaFavoriteDao
}