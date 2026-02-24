package domain.usecase.database

import domain.model.Product
import domain.repository.InventoryRepository

/**
 * A simple use case for adding sample products to the inventory.  The
 * repository implementation may ignore duplicates or overwrite existing
 * entries.
 */
class SeedDatabaseUseCase(
    private val inventoryRepository: InventoryRepository
) {
    suspend operator fun invoke(samples: List<Product>) {
        // naive implementation uses updateProductStock to insert/adjust stock
        samples.forEach { product ->
            inventoryRepository.updateProductStock(product.id, product.stock)
        }
    }
}
