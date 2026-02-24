package domain.usecase.inventory

import domain.model.Product
import domain.repository.InventoryRepository

class GetLowStockProductsUseCase(
    private val inventoryRepository: InventoryRepository
) {
    suspend operator fun invoke(threshold: Int = 10): List<Product> =
        inventoryRepository.getLowStockProducts(threshold)
}
