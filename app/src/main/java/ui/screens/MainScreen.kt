package ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import ui.viewmodel.BorneoPosViewModel
import ui.components.NavigationSidebar
import ui.components.OrderSummarySidebar
import domain.models.PosMode
import domain.models.RestaurantView

@Composable
fun BorneoPosApp(viewModel: BorneoPosViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    Row(modifier = Modifier.fillMaxSize().background(Color(0xFFF8FAFC))) {
        
        // --- Sidebar Navigation ---
        NavigationSidebar(
            currentMode = viewModel.posMode,
            onModeChange = { viewModel.switchMode(it) }
        )

        // --- Main Controller ---
        Box(modifier = Modifier.weight(1f)) {
            if (viewModel.posMode == PosMode.RESTAURANT && viewModel.restaurantView == RestaurantView.TABLES) {
                ui.components.FloorPlanScreen(viewModel)
            } else {
                Row(modifier = Modifier.fillMaxSize()) {
                    // Selection Area logic placeholder
                    Box(modifier = Modifier.weight(1f)) {
                        Text("Product Selection Area", modifier = Modifier.align(Alignment.Center))
                    }
                    OrderSummarySidebar(modifier = Modifier.width(380.dp), viewModel)
                }
            }
        }
    }
}
