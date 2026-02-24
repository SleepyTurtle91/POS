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
import data.local.entity.ProductEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BorneoDatabase {
        val db = Room.databaseBuilder(
            context,
            BorneoDatabase::class.java,
            "borneo.db"
        ).build()

        // seed with example products if empty
        CoroutineScope(Dispatchers.IO).launch {
            val dao = db.productDao()
            if (dao.getAll().isEmpty()) {
                dao.insertAll(sampleProducts())
            }
        }

        return db
    }

    @Provides
    fun provideProductDao(db: BorneoDatabase): ProductDao = db.productDao()

    @Provides
    fun provideTransactionDao(db: BorneoDatabase): TransactionDao = db.transactionDao()

    private fun sampleProducts(): List<ProductEntity> = listOf(
        ProductEntity(
            id = "p1",
            name = "Coffee",
            sku = "CF-001",
            price = 2.5,
            stock = 100,
            categoryId = "c1"
        ),
        ProductEntity(
            id = "p2",
            name = "Tea",
            sku = "TE-002",
            price = 2.0,
            stock = 150,
            categoryId = "c1"
        ),
        ProductEntity(
            id = "p3",
            name = "Sandwich",
            sku = "SW-003",
            price = 5.0,
            stock = 50,
            categoryId = "c2"
        )
    )
}
