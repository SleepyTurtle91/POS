package data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import data.local.BorneoDatabase
import data.local.dao.ProductDao
import data.local.dao.TransactionDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BorneoDatabase {
        return Room.databaseBuilder(
            context,
            BorneoDatabase::class.java,
            "borneo.db"
        ).build()
    }

    @Provides
    fun provideProductDao(db: BorneoDatabase): ProductDao = db.productDao()

    @Provides
    fun provideTransactionDao(db: BorneoDatabase): TransactionDao = db.transactionDao()
}
