package data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import domain.model.Product

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    val name: String,
    val sku: String,
    val price: Double,
    val stock: Int,
    val categoryId: String
) {
    fun toDomain(): Product =
        Product(id, name, sku, price, stock, categoryId)
}

fun Product.toEntity(): ProductEntity =
    ProductEntity(id, name, sku, price, stock, categoryId)
