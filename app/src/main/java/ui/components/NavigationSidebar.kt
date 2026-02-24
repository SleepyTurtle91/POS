package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import domain.models.PosMode

@Composable
fun NavigationSidebar(currentMode: PosMode, onModeChange: (PosMode) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(80.dp)
            .background(Color(0xFF064E3B)) // emerald-900
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Logo
        Box(
            modifier = Modifier.size(48.dp).background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("B", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        // Nav Icons
        NavIcon(Icons.Default.Restaurant, currentMode == PosMode.RESTAURANT) { onModeChange(PosMode.RESTAURANT) }
        NavIcon(Icons.Default.Coffee, currentMode == PosMode.CAFE) { onModeChange(PosMode.CAFE) }
        NavIcon(Icons.Default.ShoppingBag, currentMode == PosMode.RETAIL) { onModeChange(PosMode.RETAIL) }
        
        Spacer(modifier = Modifier.weight(1f))
        
        NavIcon(Icons.Default.Person, false) {}
        NavIcon(Icons.Default.Settings, false) {}
    }
}

@Composable
fun NavIcon(icon: androidx.compose.ui.graphics.vector.ImageVector, isActive: Boolean, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(52.dp)
            .background(
                if (isActive) Color.White else Color.Transparent,
                RoundedCornerShape(12.dp)
            )
    ) {
        Icon(icon, contentDescription = null, tint = if (isActive) Color(0xFF064E3B) else Color(0xFFA7F3D0))
    }
}
