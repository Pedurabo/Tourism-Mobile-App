package com.example.tourism.ui.booking

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tourism.data.model.ServiceType
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceBookingScreen(
    serviceId: String,
    serviceType: ServiceType,
    unitPrice: Double,
    viewModel: BookingViewModel,
    userId: String,
    onBookingSuccess: () -> Unit
) {
    var numberOfUnits by remember { mutableIntStateOf(1) }
    var specialRequirements by remember { mutableStateOf("") }
    
    var showStartDatePicker by remember { mutableStateOf(false) }
    var startDate by remember { mutableLongStateOf(System.currentTimeMillis()) }
    
    var showEndDatePicker by remember { mutableStateOf(false) }
    var endDate by remember { mutableLongStateOf(System.currentTimeMillis() + 86400000) } // Default +1 day
    
    var showConfirmation by remember { mutableStateOf(false) }
    
    val uiState by viewModel.uiState.collectAsState()
    
    val dateFormatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    
    // Calculate total price based on service type
    val totalPrice = when(serviceType) {
        ServiceType.HOTEL, ServiceType.CAR_HIRE -> {
            val diff = endDate - startDate
            val days = (diff / (1000 * 60 * 60 * 24)).coerceAtLeast(1)
            unitPrice * days * numberOfUnits
        }
        else -> unitPrice * numberOfUnits.toDouble()
    }

    val title = when(serviceType) {
        ServiceType.HOTEL -> "Hotel Reservation"
        ServiceType.CAR_HIRE -> "Car Rental"
        ServiceType.FLIGHT -> "Flight Booking"
        ServiceType.AIRPORT_PICKUP -> "Airport Transfer"
        else -> "Book Service"
    }

    val unitLabel = when(serviceType) {
        ServiceType.HOTEL -> "Number of Rooms"
        ServiceType.CAR_HIRE -> "Number of Vehicles"
        ServiceType.FLIGHT -> "Number of Passengers"
        ServiceType.AIRPORT_PICKUP -> "Number of People"
        else -> "Units"
    }

    val showReturnDate = serviceType == ServiceType.HOTEL || serviceType == ServiceType.CAR_HIRE

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Date Selection
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = if(showReturnDate) "Check-in" else "Date", fontWeight = FontWeight.SemiBold)
                    OutlinedButton(
                        onClick = { showStartDatePicker = true },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp)
                    ) {
                        Icon(Icons.Default.DateRange, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(text = dateFormatter.format(Date(startDate)), fontSize = 14.sp)
                    }
                }
                
                if (showReturnDate) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Check-out", fontWeight = FontWeight.SemiBold)
                        OutlinedButton(
                            onClick = { showEndDatePicker = true },
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp)
                        ) {
                            Icon(Icons.Default.DateRange, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text(text = dateFormatter.format(Date(endDate)), fontSize = 14.sp)
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Quantity Selection
            Text(text = unitLabel, fontWeight = FontWeight.SemiBold)
            Card(
                modifier = Modifier.padding(vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    IconButton(onClick = { if (numberOfUnits > 1) numberOfUnits-- }) {
                        Icon(Icons.Default.Remove, contentDescription = "Decrease")
                    }
                    Text(
                        text = numberOfUnits.toString(),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(onClick = { numberOfUnits++ }) {
                        Icon(Icons.Default.Add, contentDescription = "Increase")
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Special Requirements
            Text(text = "Special Requests", fontWeight = FontWeight.SemiBold)
            OutlinedTextField(
                value = specialRequirements,
                onValueChange = { specialRequirements = it },
                modifier = Modifier.fillMaxWidth().height(120.dp).padding(vertical = 8.dp),
                placeholder = { 
                    Text(when(serviceType) {
                        ServiceType.HOTEL -> "e.g. Quiet room, extra bed..."
                        ServiceType.CAR_HIRE -> "e.g. GPS, child seat..."
                        ServiceType.AIRPORT_PICKUP -> "Please provide your flight number and arrival time."
                        else -> "Any special requirements?"
                    }) 
                },
                shape = RoundedCornerShape(12.dp)
            )
            
            Spacer(modifier = Modifier.height(32.dp))

            // Price Summary Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Rate per ${if(serviceType == ServiceType.HOTEL) "night" else if(serviceType == ServiceType.CAR_HIRE) "day" else "unit"}")
                        Text("$${unitPrice}")
                    }
                    
                    if (showReturnDate) {
                        val days = ((endDate - startDate) / (1000 * 60 * 60 * 24)).coerceAtLeast(1)
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Duration")
                            Text("$days ${if(serviceType == ServiceType.HOTEL) "Nights" else "Days"}")
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(unitLabel)
                        Text("x $numberOfUnits")
                    }
                    
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.2f))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Estimated Total", fontWeight = FontWeight.Bold)
                        Text(
                            text = "$${String.format("%.2f", totalPrice)}",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.createServiceBooking(
                        userId = userId,
                        serviceId = serviceId,
                        serviceType = serviceType,
                        totalAmount = totalPrice,
                        travelDate = Date(startDate),
                        numberOfUnits = numberOfUnits,
                        returnDate = if (showReturnDate) Date(endDate) else null,
                        specialRequirements = specialRequirements
                    )
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                enabled = uiState !is BookingUiState.Loading && (!showReturnDate || endDate > startDate)
            ) {
                if (uiState is BookingUiState.Loading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text("Confirm Reservation", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
            
            if (showReturnDate && endDate <= startDate) {
                Text(
                    "Check-out date must be after check-in date",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        // Success Overlay
        LaunchedEffect(uiState) {
            if (uiState is BookingUiState.Success) {
                showConfirmation = true
                delay(2000)
                onBookingSuccess()
            }
        }

        AnimatedVisibility(
            visible = showConfirmation,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier.padding(32.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier.padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            Icons.Default.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = Color(0xFF4CAF50)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            "Booking Confirmed!",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Your reservation was successful",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }

    if (showStartDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showStartDatePicker = false },
            confirmButton = {
                TextButton(onClick = { showStartDatePicker = false }) {
                    Text("OK")
                }
            }
        ) {
            val datePickerState = rememberDatePickerState(initialSelectedDateMillis = startDate)
            DatePicker(state = datePickerState)
            LaunchedEffect(datePickerState.selectedDateMillis) {
                datePickerState.selectedDateMillis?.let { startDate = it }
            }
        }
    }

    if (showEndDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showEndDatePicker = false },
            confirmButton = {
                TextButton(onClick = { showEndDatePicker = false }) {
                    Text("OK")
                }
            }
        ) {
            val datePickerState = rememberDatePickerState(initialSelectedDateMillis = endDate)
            DatePicker(state = datePickerState)
            LaunchedEffect(datePickerState.selectedDateMillis) {
                datePickerState.selectedDateMillis?.let { endDate = it }
            }
        }
    }
}
