package ui.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

import domain.models.CartItem
import domain.models.Product
import domain.models.Table
import domain.models.DiningOption
import domain.models.PosMode
import domain.models.RestaurantView

class BorneoPosViewModel : ViewModel() {
    // UI State
    var posMode by mutableStateOf(PosMode.RESTAURANT)
    var restaurantView by mutableStateOf(RestaurantView.TABLES)
    var activeTableId by mutableStateOf<Int?>(null)
    var diningOption by mutableStateOf(DiningOption.DINE_IN)
    var searchQuery by mutableStateOf("")
    var activeCategory by mutableStateOf("All")
    
    // Data State (Empty by default, to be populated by Repository)
    private val _products = mutableStateListOf<Product>()
    val products: List<Product> get() = _products

    private val _tables = mutableStateListOf<Table>()
    val tables: List<Table> get() = _tables

    private val _cart = mutableStateListOf<CartItem>()
    val cart: List<CartItem> get() = _cart

    // Computed Logic
    val subtotal get() = _cart.sumOf { it.product.price * it.quantity }
    val tax get() = subtotal * 0.06
    val total get() = subtotal + tax

    // Data Setters (Used by Repository/Activity to inject data)
    fun loadProducts(items: List<Product>) {
        _products.clear()
        _products.addAll(items)
    }

    fun loadTables(items: List<Table>) {
        _tables.clear()
        _tables.addAll(items)
    }

    // Actions
    fun addToCart(product: Product) {
        val existing = _cart.find { it.product.id == product.id }
        if (existing != null) {
            existing.quantity++
        } else {
            _cart.add(CartItem(product, 1))
        }
    }

    fun updateQuantity(productId: Int, delta: Int) {
        val itemIndex = _cart.indexOfFirst { it.product.id == productId }
        if (itemIndex != -1) {
            val item = _cart[itemIndex]
            val newQty = item.quantity + delta
            if (newQty <= 0) {
                _cart.removeAt(itemIndex)
            } else {
                _cart[itemIndex] = item.copy(quantity = newQty)
            }
        }
    }

    fun handleTableSelect(table: Table) {
        activeTableId = table.id
        _cart.clear()
        _cart.addAll(table.savedCart)
        restaurantView = RestaurantView.ORDER
    }

    fun saveOrder() {
        val tableIndex = _tables.indexOfFirst { it.id == activeTableId }
        if (tableIndex != -1) {
            _tables[tableIndex] = _tables[tableIndex].copy(
                status = if (_cart.isNotEmpty()) "occupied" else "available",
                savedCart = _cart.toList()
            )
        }
        restaurantView = RestaurantView.TABLES
        _cart.clear()
        activeTableId = null
    }

    fun completePayment() {
        if (posMode == PosMode.RESTAURANT && activeTableId != null) {
            val tableIndex = _tables.indexOfFirst { it.id == activeTableId }
            if (tableIndex != -1) {
                _tables[tableIndex] = _tables[tableIndex].copy(status = "available", savedCart = emptyList())
            }
        }
        _cart.clear()
        activeTableId = null
        if (posMode == PosMode.RESTAURANT) restaurantView = RestaurantView.TABLES
    }

    fun switchMode(mode: PosMode) {
        posMode = mode
        if (mode == PosMode.RESTAURANT) restaurantView = RestaurantView.TABLES
        activeTableId = null
        if (mode != PosMode.RESTAURANT) {
            _cart.clear() // Clear scratchpad for non-persistent modes
        }
    }
}
