package data.repository

import data.local.dao.TransactionDao
import data.local.entity.TransactionEntity
import domain.model.Transaction
import domain.repository.SalesRepository

class SalesRepositoryImpl(
    private val dao: TransactionDao
) : SalesRepository {
    private fun TransactionEntity.toDomain(): Transaction =
        Transaction(id, emptyList(), subtotal, tax, total, paymentMethod, timestamp)

    override suspend fun saveTransaction(transaction: Transaction) {
        val entity = TransactionEntity(
            id = transaction.id,
            subtotal = transaction.subtotal,
            tax = transaction.tax,
            total = transaction.total,
            paymentMethod = transaction.paymentMethod,
            timestamp = transaction.timestamp
        )
        dao.insert(entity)
    }

    override suspend fun getDailySales(epochDay: Long): List<Transaction> {
        // epochDay is presumably days since epoch; convert to millis range
        val start = epochDay * 24 * 60 * 60 * 1000
        val end = start + 24 * 60 * 60 * 1000 - 1
        return dao.getTransactionsBetween(start, end).map { it.toDomain() }
    }
}
