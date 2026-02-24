package domain.model

data class Product(
    val id: String,
    val name: String,
    val sku: String,
    val price: Double,
    val stock: Int,
    val categoryId: String
)
