package ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import ui.viewmodel.BorneoPosViewModel
import domain.models.PosMode
import domain.models.DiningOption

@Composable
fun OrderSummarySidebar(modifier: Modifier, viewModel: BorneoPosViewModel) {
    Surface(
        modifier = modifier.fillMaxHeight(),
        color = Color.White,
        shadowElevation = 16.dp
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header
            Row(modifier = Modifier.padding(24.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("CHECKOUT LIST", fontWeight = FontWeight.Black, fontSize = 18.sp)
                IconButton(onClick = { /* Clear logic */ }) {
                    Icon(Icons.Default.Delete, contentDescription = null, tint = Color.LightGray)
                }
            }

            // Dining Toggle
            Row(modifier = Modifier.padding(horizontal = 24.dp).background(Color(0xFFF1F5F9), RoundedCornerShape(12.dp)).padding(4.dp)) {
                DiningButton("DINE IN", viewModel.diningOption == DiningOption.DINE_IN) { viewModel.diningOption = DiningOption.DINE_IN }
                DiningButton("TAKEAWAY", viewModel.diningOption == DiningOption.TAKEAWAY) { viewModel.diningOption = DiningOption.TAKEAWAY }
            }

            // Cart Items List
            LazyColumn(modifier = Modifier.weight(1f).padding(24.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                items(viewModel.cart) { item ->
                    // UI for Cart Item Row
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(item.product.emoji, fontSize = 24.sp)
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(item.product.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text("RM ${String.format("%.2f", item.product.price * item.quantity)}", color = Color(0xFF059669), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.background(Color(0xFFF8FAFC), RoundedCornerShape(8.dp)).border(1.dp, Color(0xFFF1F5F9), RoundedCornerShape(8.dp))) {
                            IconButton(onClick = { viewModel.updateQuantity(item.product.id, -1) }, modifier = Modifier.size(32.dp)) { Icon(Icons.Default.Remove, null, modifier = Modifier.size(16.dp)) }
                            Text("${item.quantity}", fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 4.dp))
                            IconButton(onClick = { viewModel.updateQuantity(item.product.id, 1) }, modifier = Modifier.size(32.dp)) { Icon(Icons.Default.Add, null, modifier = Modifier.size(16.dp)) }
                        }
                    }
                }
            }

            // Footer / Totals
            Column(modifier = Modifier.background(Color(0xFFF8FAFC)).padding(24.dp)) {
                TotalRow("Subtotal", viewModel.subtotal)
                TotalRow("SST (6%)", viewModel.tax)
                Divider(modifier = Modifier.padding(vertical = 16.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Total", fontWeight = FontWeight.Black, fontSize = 24.sp)
                    Text("RM ${String.format("%.2f", viewModel.total)}", color = Color(0xFF047857), fontWeight = FontWeight.Black, fontSize = 24.sp)
                }
                
                Spacer(modifier = Modifier.height(24.dp))

                if (viewModel.posMode == PosMode.RESTAURANT) {
                    Button(
                        onClick = { viewModel.saveOrder() },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD1FAE5)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(Icons.Default.Save, contentDescription = null, tint = Color(0xFF065F46))
                        Spacer(Modifier.width(8.dp))
                        Text("SAVE ORDER", color = Color(0xFF065F46), fontWeight = FontWeight.Black)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Button(
                    onClick = { viewModel.completePayment() },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF047857)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("PAY NOW", fontWeight = FontWeight.Black)
                    Spacer(Modifier.width(8.dp))
                    Icon(Icons.Default.ChevronRight, contentDescription = null)
                }
            }
        }
    }
}

@Composable
fun TotalRow(label: String, value: Double) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Text("RM ${String.format("%.2f", value)}", fontWeight = FontWeight.Bold, fontSize = 12.sp)
    }
}

@Composable
fun DiningButton(label: String, isActive: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .weight(1f)
            .height(40.dp)
            .background(if (isActive) Color.White else Color.Transparent, RoundedCornerShape(8.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(label, fontSize = 10.sp, fontWeight = FontWeight.Black, color = if (isActive) Color(0xFF047857) else Color.Gray)
    }
}
