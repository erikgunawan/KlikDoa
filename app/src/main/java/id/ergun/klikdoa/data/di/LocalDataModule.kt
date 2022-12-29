package id.ergun.klikdoa.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.ergun.klikdoa.common.Util.FAVORITE_DOA_TABLE_NAME
import id.ergun.klikdoa.data.local.KlikDoaDB
import id.ergun.klikdoa.data.local.doa.DoaFavoriteDao
import id.ergun.klikdoa.data.repository.DoaRepository

/**
 * @author erikgunawan
 * Created 29/12/22 at 09.19
 */
@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Provides
    fun provideKlikDoaDB(
        @ApplicationContext
        context : Context
    ) = Room.databaseBuilder(
        context,
        KlikDoaDB::class.java,
        FAVORITE_DOA_TABLE_NAME
    ).build()

    @Provides
    fun provideDoaFavoriteDao(
        klikDoaDB: KlikDoaDB
    ) = klikDoaDB.favoriteDoaDao()

    @Provides
    fun provideDoaRepository(
        doaFavoriteDao: DoaFavoriteDao
    ): DoaRepository = DoaRepository(
        doaFavoriteDao = doaFavoriteDao
    )
}