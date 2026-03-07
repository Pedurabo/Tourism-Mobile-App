package com.example.tourism.ui.booking

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tourism.data.model.Booking
import com.example.tourism.data.model.PaymentMethod

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentSelectionScreen(
    booking: Booking,
    viewModel: PaymentViewModel,
    onPaymentComplete: (String) -> Unit
) {
    var selectedMethod by remember { mutableStateOf<PaymentMethod?>(null) }
    val paymentState by viewModel.paymentState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Payment Method",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        
        Text(
            text = "Select your preferred payment method",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
        )

        PaymentMethodItem(
            name = "Airtel Money",
            description = "Pay using your Airtel number",
            isSelected = selectedMethod == PaymentMethod.MOBILE_MONEY_AIRTEL,
            onClick = { selectedMethod = PaymentMethod.MOBILE_MONEY_AIRTEL }
        )

        PaymentMethodItem(
            name = "Credit / Debit Card",
            description = "Visa, Mastercard, Amex",
            isSelected = selectedMethod == PaymentMethod.CREDIT_CARD,
            onClick = { selectedMethod = PaymentMethod.CREDIT_CARD }
        )

        PaymentMethodItem(
            name = "MTN Mobile Money",
            description = "Pay using your MTN number",
            isSelected = selectedMethod == PaymentMethod.MOBILE_MONEY_MTN,
            onClick = { selectedMethod = PaymentMethod.MOBILE_MONEY_MTN }
        )

        Spacer(modifier = Modifier.weight(1f))

        Card(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Amount to Pay", fontWeight = FontWeight.Medium)
                Text(
                    text = "UGX ${String.format("%,.0f", booking.totalAmount * 3700)}", // Mock UGX conversion
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }

        Button(
            onClick = { 
                selectedMethod?.let { viewModel.processPayment(booking, it) }
            },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            enabled = selectedMethod != null && paymentState !is PaymentUiState.Processing,
            shape = RoundedCornerShape(12.dp)
        ) {
            if (paymentState is PaymentUiState.Processing) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
            } else {
                Text("Pay Now", fontSize = 18.sp)
            }
        }
    }

    LaunchedEffect(paymentState) {
        if (paymentState is PaymentUiState.Success) {
            onPaymentComplete((paymentState as PaymentUiState.Success).payment.transactionReference)
        }
    }
}

@Composable
fun PaymentMethodItem(
    name: String,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        border = CardDefaults.outlinedCardBorder(
            enabled = isSelected
        ),
        colors = CardDefaults.outlinedCardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f) else Color.Transparent
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = name, fontWeight = FontWeight.Bold)
                Text(text = description, style = MaterialTheme.typography.bodySmall)
            }
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
