package id.ergun.klikdoa.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ergun.klikdoa.data.local.doa.DoaFavorite
import id.ergun.klikdoa.data.local.doa.DoaFavoriteDao

/**
 * @author erikgunawan
 * Created 29/12/22 at 09.20
 */
@Database(entities = [DoaFavorite::class], version = 1, exportSchema = false)
abstract class KlikDoaDB : RoomDatabase() {
    abstract fun favoriteDoaDao(): DoaFavoriteDao
}