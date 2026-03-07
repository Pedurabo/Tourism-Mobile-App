package com.example.tourism.ui.admin

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tourism.data.model.*
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel
) {
    val dashboardState by viewModel.dashboardState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val statusFilter by viewModel.bookingStatusFilter.collectAsState()
    
    var showAddSiteDialog by remember { mutableStateOf(false) }
    var landmarkToEdit by remember { mutableStateOf<Landmark?>(null) }
    var landmarkToDelete by remember { mutableStateOf<Landmark?>(null) }
    
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is DashboardUiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("PearlGuide Admin", fontWeight = FontWeight.ExtraBold) },
                actions = {
                    IconButton(onClick = { viewModel.exportReport() }) {
                        Icon(Icons.Default.IosShare, contentDescription = "Export Report")
                    }
                    IconButton(onClick = { viewModel.loadDashboardData() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddSiteDialog = true },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Site")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
        ) {
            when (dashboardState) {
                is DashboardUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is DashboardUiState.Success -> {
                    val successData = dashboardState as DashboardUiState.Success
                    
                    var selectedTab by remember { mutableStateOf(0) }
                    val tabs = listOf("Bookings", "Inventory", "Users")

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Analytics Section
                        item {
                            AnalyticsSection(successData)
                        }

                        // Tab Section
                        item {
                            TabRow(
                                selectedTabIndex = selectedTab,
                                containerColor = Color.Transparent,
                                contentColor = MaterialTheme.colorScheme.primary,
                                divider = {},
                                indicator = { tabPositions ->
                                    if (selectedTab < tabPositions.size) {
                                        TabRowDefaults.SecondaryIndicator(
                                            modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                            ) {
                                tabs.forEachIndexed { index, title ->
                                    Tab(
                                        selected = selectedTab == index,
                                        onClick = { selectedTab = index },
                                        text = { Text(title, fontWeight = FontWeight.Bold) }
                                    )
                                }
                            }
                        }

                        // Search/Filter Controls
                        item {
                            AnimatedVisibility(visible = selectedTab == 0) {
                                StatusFilterSection(
                                    selectedStatus = statusFilter,
                                    onStatusSelected = { viewModel.setBookingStatusFilter(it) }
                                )
                            }
                            AnimatedVisibility(visible = selectedTab == 1 || selectedTab == 2) {
                                OutlinedTextField(
                                    value = searchQuery,
                                    onValueChange = { viewModel.setSearchQuery(it) },
                                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                                    placeholder = { Text(if(selectedTab == 1) "Search site..." else "Search user...") },
                                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                                    trailingIcon = {
                                        if (searchQuery.isNotEmpty()) {
                                            IconButton(onClick = { viewModel.setSearchQuery("") }) {
                                                Icon(Icons.Default.Close, contentDescription = "Clear")
                                            }
                                        }
                                    },
                                    shape = RoundedCornerShape(12.dp),
                                    singleLine = true,
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        containerColor = MaterialTheme.colorScheme.surface
                                    )
                                )
                            }
                        }

                        when (selectedTab) {
                            0 -> { // Bookings
                                if (successData.bookings.isEmpty()) {
                                    item { EmptyState("No matching bookings", Icons.Default.DateRange) }
                                } else {
                                    items(successData.bookings, key = { it.id }) { booking ->
                                        BookingAdminItem(
                                            booking = booking,
                                            onConfirm = { viewModel.updateBookingStatus(booking.id, BookingStatus.CONFIRMED) },
                                            onCancel = { viewModel.updateBookingStatus(booking.id, BookingStatus.CANCELLED) }
                                        )
                                    }
                                }
                            }
                            1 -> { // Inventory
                                if (successData.landmarks.isEmpty()) {
                                    item { EmptyState("No matching landmarks", Icons.Default.Landscape) }
                                } else {
                                    items(successData.landmarks, key = { it.id }) { landmark ->
                                        LandmarkAdminItem(
                                            landmark = landmark,
                                            onEdit = { landmarkToEdit = it },
                                            onDelete = { landmarkToDelete = it }
                                        )
                                    }
                                }
                            }
                            2 -> { // Users
                                if (successData.users.isEmpty()) {
                                    item { EmptyState("No matching users found", Icons.Default.People) }
                                } else {
                                    items(successData.users, key = { it.id }) { user ->
                                        UserAdminItem(
                                            user = user,
                                            onRoleChange = { newRole -> viewModel.updateUserRole(user, newRole) }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                is DashboardUiState.Error -> {
                    EmptyState(
                        message = (dashboardState as DashboardUiState.Error).message,
                        icon = Icons.Default.Warning
                    )
                }
            }
        }
    }

    if (showAddSiteDialog) {
        AddSiteDialog(
            onDismiss = { showAddSiteDialog = false },
            onAdd = { name, location, desc, cat, img, price, lat, long ->
                viewModel.addTourismSite(name, location, desc, cat, img, price, lat, long)
                showAddSiteDialog = false
            }
        )
    }

    landmarkToEdit?.let { landmark ->
        EditSiteDialog(
            landmark = landmark,
            onDismiss = { landmarkToEdit = null },
            onSave = { updatedLandmark ->
                viewModel.updateLandmark(updatedLandmark)
                landmarkToEdit = null
            }
        )
    }

    landmarkToDelete?.let { landmark ->
        AlertDialog(
            onDismissRequest = { landmarkToDelete = null },
            title = { Text("Delete Site") },
            text = { Text("Are you sure you want to delete ${landmark.name}? This action cannot be undone.") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteLandmark(landmark)
                        landmarkToDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { landmarkToDelete = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun UserAdminItem(
    user: User,
    onRoleChange: (UserType) -> Unit
) {
    var showRoleDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(40.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(user.fullName.take(1).uppercase(), fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(user.fullName, fontWeight = FontWeight.Bold)
                Text(user.email, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
            
            TextButton(onClick = { showRoleDialog = true }) {
                Surface(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            user.userType.name, 
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    }

    if (showRoleDialog) {
        AlertDialog(
            onDismissRequest = { showRoleDialog = false },
            title = { Text("Change Role for ${user.fullName.take(15)}...") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    UserType.values().forEach { role ->
                        val isSelected = user.userType == role
                        Button(
                            onClick = { 
                                onRoleChange(role)
                                showRoleDialog = false 
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = if (isSelected) ButtonDefaults.buttonColors() else ButtonDefaults.outlinedButtonColors(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                if (isSelected) {
                                    Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(18.dp))
                                    Spacer(Modifier.width(8.dp))
                                }
                                Text(role.name)
                            }
                        }
                    }
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { showRoleDialog = false }) { Text("Cancel") }
            },
            shape = RoundedCornerShape(24.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusFilterSection(
    selectedStatus: BookingStatus?,
    onStatusSelected: (BookingStatus?) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            FilterChip(
                selected = selectedStatus == null,
                onClick = { onStatusSelected(null) },
                label = { Text("All") },
                shape = RoundedCornerShape(12.dp)
            )
        }
        val statusList = BookingStatus.values()
        items(statusList.size) { index ->
            val status = statusList[index]
            FilterChip(
                selected = selectedStatus == status,
                onClick = { onStatusSelected(status) },
                label = { Text(status.name.lowercase().replaceFirstChar { it.uppercase() }) },
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}

@Composable
fun AnalyticsSection(data: DashboardUiState.Success) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("System Insights", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(
                label = "Total Revenue",
                value = "$${String.format("%,.0f", data.totalRevenue)}",
                icon = Icons.Default.Payments,
                modifier = Modifier.weight(1.2f),
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
            StatCard(
                label = "Active Tours",
                value = "${data.activeTours}",
                icon = Icons.Default.Hiking,
                modifier = Modifier.weight(1f),
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
            StatCard(
                label = "Pending",
                value = "${data.pendingBookings}",
                icon = Icons.Default.PendingActions,
                modifier = Modifier.weight(1f),
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        }
        
        // Revenue Category Breakdown (Simplified Visualization)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Revenue by Site Category", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
                Spacer(modifier = Modifier.height(16.dp))
                
                // Categorize data for breakdown
                val categoryRevenue = data.landmarks.associateWith { landmark ->
                    data.bookings.filter { it.serviceId == landmark.id && it.serviceType == ServiceType.LANDMARK && it.status != BookingStatus.CANCELLED }.sumOf { it.totalAmount }
                }.entries.groupBy { it.key.category }.mapValues { it.value.sumOf { entry -> entry.value } }

                if (categoryRevenue.isNotEmpty()) {
                    val maxRev = categoryRevenue.values.maxOrNull() ?: 1.0
                    categoryRevenue.forEach { (cat, rev) ->
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
                            Text(cat, modifier = Modifier.width(80.dp), style = MaterialTheme.typography.labelSmall)
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(8.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth((rev / maxRev).toFloat().coerceIn(0.05f, 1f))
                                        .fillMaxHeight()
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.primary)
                                )
                            }
                            Text(
                                "$${String.format("%.0f", rev)}", 
                                modifier = Modifier.padding(start = 8.dp), 
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                } else {
                    Text("No revenue data available", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun EmptyState(message: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(64.dp), tint = Color.LightGray)
        Spacer(modifier = Modifier.height(16.dp))
        Text(message, color = Color.Gray, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun AddSiteDialog(
    onDismiss: () -> Unit,
    onAdd: (String, String, String, String, String, Double, Double, Double) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var latitude by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> uri?.let { imageUrl = it.toString() } }
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("New Tourism Site", fontWeight = FontWeight.Bold) },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()).fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Image Preview and Picker
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp))
                        .clickable {
                            photoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUrl.isNotBlank()) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(8.dp)
                                .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                                .padding(4.dp)
                        ) {
                            Icon(Icons.Default.Edit, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                        }
                    } else {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.AddAPhoto, contentDescription = null, tint = Color.Gray)
                            Text("Add Photo", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
                        }
                    }
                }

                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Site Name") }, shape = RoundedCornerShape(12.dp))
                OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Location") }, shape = RoundedCornerShape(12.dp))
                OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") }, shape = RoundedCornerShape(12.dp))
                OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Tour Price ($)") }, shape = RoundedCornerShape(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = latitude, onValueChange = { latitude = it }, label = { Text("Lat") }, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp))
                    OutlinedTextField(value = longitude, onValueChange = { longitude = it }, label = { Text("Long") }, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp))
                }
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.height(100.dp),
                    shape = RoundedCornerShape(12.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { 
                    onAdd(
                        name, location, description, category, imageUrl, 
                        price.toDoubleOrNull() ?: 0.0,
                        latitude.toDoubleOrNull() ?: 0.0,
                        longitude.toDoubleOrNull() ?: 0.0
                    ) 
                },
                enabled = name.isNotBlank() && location.isNotBlank(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Create Site")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        },
        shape = RoundedCornerShape(28.dp)
    )
}

@Composable
fun EditSiteDialog(
    landmark: Landmark,
    onDismiss: () -> Unit,
    onSave: (Landmark) -> Unit
) {
    var name by remember { mutableStateOf(landmark.name) }
    var location by remember { mutableStateOf(landmark.location) }
    var description by remember { mutableStateOf(landmark.description) }
    var category by remember { mutableStateOf(landmark.category) }
    var imageUrl by remember { mutableStateOf(landmark.imageUrl) }
    var price by remember { mutableStateOf(landmark.tourPrice.toString()) }
    var latitude by remember { mutableStateOf(landmark.latitude.toString()) }
    var longitude by remember { mutableStateOf(landmark.longitude.toString()) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> uri?.let { imageUrl = it.toString() } }
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Tourism Site", fontWeight = FontWeight.Bold) },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()).fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Image Preview and Picker
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp))
                        .clickable {
                            photoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp)
                            .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                            .padding(4.dp)
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                    }
                }

                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Site Name") }, shape = RoundedCornerShape(12.dp))
                OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Location") }, shape = RoundedCornerShape(12.dp))
                OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") }, shape = RoundedCornerShape(12.dp))
                OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Tour Price ($)") }, shape = RoundedCornerShape(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = latitude, onValueChange = { latitude = it }, label = { Text("Lat") }, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp))
                    OutlinedTextField(value = longitude, onValueChange = { longitude = it }, label = { Text("Long") }, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp))
                }
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.height(100.dp),
                    shape = RoundedCornerShape(12.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { 
                    onSave(
                        landmark.copy(
                            name = name,
                            location = location,
                            description = description,
                            category = category,
                            imageUrl = imageUrl,
                            tourPrice = price.toDoubleOrNull() ?: 0.0,
                            latitude = latitude.toDoubleOrNull() ?: 0.0,
                            longitude = longitude.toDoubleOrNull() ?: 0.0
                        )
                    ) 
                },
                enabled = name.isNotBlank() && location.isNotBlank(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Save Changes")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        },
        shape = RoundedCornerShape(28.dp)
    )
}

@Composable
fun LandmarkAdminItem(
    landmark: Landmark,
    onEdit: (Landmark) -> Unit,
    onDelete: (Landmark) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = landmark.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(70.dp).clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = landmark.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = landmark.location, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                Text(
                    text = landmark.category, 
                    style = MaterialTheme.typography.labelSmall, 
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Row {
                IconButton(onClick = { onEdit(landmark) }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.Gray, modifier = Modifier.size(20.dp))
                }
                IconButton(onClick = { onDelete(landmark) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}

@Composable
fun StatCard(label: String, value: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier = Modifier, containerColor: Color) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = containerColor),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(20.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold)
            Text(text = label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f))
        }
    }
}

@Composable
fun BookingAdminItem(
    booking: Booking,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    val dateFormatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Booking #${booking.id.take(8).uppercase()}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "User: ${booking.userId.take(8)}",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                }
                StatusBadge(status = booking.status)
            }
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), thickness = 0.5.dp, color = Color.LightGray.copy(alpha = 0.5f))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BookingInfoDetail(Icons.Default.DateRange, dateFormatter.format(booking.travelDate))
                BookingInfoDetail(Icons.Default.Person, "${booking.numberOfPeople} Units")
                BookingInfoDetail(Icons.Default.ShoppingCart, "$${String.format("%,.0f", booking.totalAmount)}")
            }

            if (booking.status == BookingStatus.PENDING) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onCancel,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
                    ) {
                        Text("Decline")
                    }
                    Button(
                        onClick = onConfirm,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}

@Composable
fun BookingInfoDetail(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(14.dp), tint = Color.Gray)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun StatusBadge(status: BookingStatus) {
    val (color, label) = when (status) {
        BookingStatus.CANCELLED -> Color.Red to "Cancelled"
        BookingStatus.COMPLETED -> Color.Blue to "Done"
        BookingStatus.CONFIRMED -> Color(0xFF4CAF50) to "Confirmed"
        BookingStatus.PENDING -> Color(0xFFFFA500) to "Pending"
    }
    
    Surface(
        color = color.copy(alpha = 0.1f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = label,
            color = color,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
