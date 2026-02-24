package domain.usecase.printer

import domain.model.Transaction

class GenerateReceiptUseCase {
    operator fun invoke(transaction: Transaction): String {
        return buildString {
            appendLine("Receipt #${transaction.id}")
            appendLine("Total: ${transaction.total}")
        }
    }
}
