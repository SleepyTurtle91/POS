package data.remote.dto

data class ProductDto(
    val id: String,
    val name: String,
    val sku: String,
    val price: Double,
    val stock: Int,
    val categoryId: String
)
