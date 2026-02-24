package domain.repository

import domain.model.Product

interface InventoryRepository {
    suspend fun getAllProducts(): List<Product>
    suspend fun getLowStockProducts(threshold: Int = 10): List<Product>
    suspend fun updateProductStock(productId: String, quantity: Int)
}
