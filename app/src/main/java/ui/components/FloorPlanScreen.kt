package ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import ui.viewmodel.BorneoPosViewModel

@Composable
fun FloorPlanScreen(viewModel: BorneoPosViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(32.dp)) {
        Text("FLOOR PLAN", fontWeight = FontWeight.Black, fontSize = 30.sp, color = Color(0xFF064E3B))
        Text("Monitor active sessions and open tables", color = Color.Gray, modifier = Modifier.padding(bottom = 32.dp))

        if (viewModel.tables.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No tables configured", color = Color.LightGray)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(6),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(viewModel.tables) { table ->
                    val isOccupied = table.status == "occupied"
                    val tableTotal = table.savedCart.sumOf { it.product.price * it.quantity }

                    Card(
                        onClick = { viewModel.handleTableSelect(table) },
                        modifier = Modifier.aspectRatio(1f),
                        shape = RoundedCornerShape(40.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isOccupied) Color(0xFF10B981) else Color.White
                        ),
                        border = BorderStroke(2.dp, if (isOccupied) Color(0xFF059669) else Color(0xFFF1F5F9))
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text("TABLE", fontSize = 10.sp, fontWeight = FontWeight.Black, color = if (isOccupied) Color.White.copy(0.6f) else Color.Gray)
                            Text(table.name.split("-").getOrElse(1) { table.name }, fontSize = 36.sp, fontWeight = FontWeight.Black, color = if (isOccupied) Color.White else Color.Black)
                            if (isOccupied) {
                                Text("RM ${String.format("%.2f", tableTotal)}", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}
