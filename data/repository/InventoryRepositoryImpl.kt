package data.repository

import data.local.dao.ProductDao
import data.local.entity.ProductEntity
import domain.model.Product
import domain.repository.InventoryRepository

class InventoryRepositoryImpl(
    private val dao: ProductDao
) : InventoryRepository {
    private fun ProductEntity.toDomain() = Product(id, name, sku, price, stock, categoryId)

    override suspend fun getAllProducts(): List<Product> {
        return dao.getAll().map { it.toDomain() }
    }

    override suspend fun getLowStockProducts(threshold: Int): List<Product> {
        return dao.getLowStock(threshold).map { it.toDomain() }
    }

    override suspend fun updateProductStock(productId: String, quantity: Int) {
        val items = dao.getAll().map { it }
        val product = items.find { it.id == productId } ?: return
        val updated = product.copy(stock = quantity)
        dao.update(updated)
    }
}
