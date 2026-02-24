package data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey val id: String,
    val subtotal: Double,
    val tax: Double,
    val total: Double,
    val paymentMethod: String,
    val timestamp: Long
)
