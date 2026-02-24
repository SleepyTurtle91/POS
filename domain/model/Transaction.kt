package domain.model

data class Transaction(
    val id: String,
    val items: List<CartItem>,
    val subtotal: Double,
    val tax: Double,
    val total: Double,
    val paymentMethod: String,
    val timestamp: Long
)
