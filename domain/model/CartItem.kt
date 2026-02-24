package domain.model

data class CartItem(
    val productId: String,
    val productName: String,
    val quantity: Int,
    val unitPrice: Double
)
