package domain.models

// enums and data types used by the sample POS UI.  This file is intentionally separate
// from the existing `domain.model` package in order to avoid colliding with the
// real business models that already exist in this repository.

enum class PosMode { RESTAURANT, CAFE, RETAIL }
enum class DiningOption { DINE_IN, TAKEAWAY }
enum class PaymentMethod { CASH, CARD, QR }
enum class RestaurantView { TABLES, ORDER }

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val category: String,
    val emoji: String,
    val stock: Int,
    val sku: String
)

data class CartItem(
    val product: Product,
    var quantity: Int
)

data class Table(
    val id: Int,
    val name: String,
    var status: String = "available", // "available" or "occupied"
    var savedCart: List<CartItem> = emptyList()
)
