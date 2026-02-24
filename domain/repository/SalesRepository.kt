package domain.repository

import domain.model.Transaction

interface SalesRepository {
    suspend fun saveTransaction(transaction: Transaction)
    suspend fun getDailySales(epochDay: Long): List<Transaction>
}
