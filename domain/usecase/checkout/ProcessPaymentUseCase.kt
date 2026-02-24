package domain.usecase.checkout

import domain.model.Transaction
import domain.repository.SalesRepository

class ProcessPaymentUseCase(
    private val salesRepository: SalesRepository
) {
    suspend operator fun invoke(transaction: Transaction) {
        salesRepository.saveTransaction(transaction)
    }
}
