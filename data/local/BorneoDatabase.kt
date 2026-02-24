package data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import data.local.dao.ProductDao
import data.local.dao.TransactionDao
import data.local.entity.ProductEntity
import data.local.entity.TransactionEntity

@Database(
    entities = [ProductEntity::class, TransactionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BorneoDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun transactionDao(): TransactionDao
}
