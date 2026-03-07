package com.example.tourism.ui.map

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tourism.data.model.Landmark
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    viewModel: MapViewModel,
    onLandmarkClick: (Landmark) -> Unit
) {
    val landmarks by viewModel.landmarks.collectAsState()
    var selectedLandmark by remember { mutableStateOf<Landmark?>(null) }
    var isUserLocationVisible by remember { mutableStateOf(false) }

    // Simulate getting user location after a delay
    LaunchedEffect(Unit) {
        delay(2000)
        isUserLocationVisible = true
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Mock Map Background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5)), // Off-white map background
            contentAlignment = Alignment.Center
        ) {
            // Simulated Map Grid/Lines
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = null,
                    modifier = Modifier.size(120.dp),
                    tint = Color.LightGray.copy(alpha = 0.3f)
                )
                Text("Uganda Explorer Map", color = Color.LightGray, fontWeight = FontWeight.Bold)
                Text("Showing ${landmarks.size} Locations", color = Color.LightGray, fontSize = 12.sp)
            }
            
            // Simulated User Location Marker
            if (isUserLocationVisible) {
                UserLocationMarker(
                    modifier = Modifier.offset(x = 10.dp, y = 50.dp) // Simulated Entebbe/Kampala area
                )
            }

            // Simulated Markers
            landmarks.forEach { landmark ->
                MapMarker(
                    landmark = landmark,
                    isSelected = selectedLandmark?.id == landmark.id,
                    onClick = { 
                        selectedLandmark = if (selectedLandmark?.id == landmark.id) null else landmark 
                    }
                )
            }
        }

        // Selected Landmark Info Card
        AnimatedVisibility(
            visible = selectedLandmark != null,
            enter = slideInVertically { it } + fadeIn(),
            exit = slideOutVertically { it } + fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            selectedLandmark?.let { landmark ->
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = landmark.name,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Text(
                                text = "📍 ${landmark.location}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "From $${landmark.tourPrice}",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        }
                        
                        Button(
                            onClick = { onLandmarkClick(landmark) },
                            shape = RoundedCornerShape(16.dp),
                            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                        ) {
                            Text("Explore")
                        }
                    }
                }
            }
        }
        
        // Floating Controls
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FloatingActionButton(
                onClick = { isUserLocationVisible = !isUserLocationVisible },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(48.dp),
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.MyLocation, contentDescription = "My Location")
            }
            
            FloatingActionButton(
                onClick = { /* Zoom In Simulation */ },
                containerColor = MaterialTheme.colorScheme.surface,
                modifier = Modifier.size(48.dp),
                shape = CircleShape
            ) {
                Text("+", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }

        // Top Info Badge
        Surface(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp),
            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.9f),
            shape = RoundedCornerShape(24.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Tap markers to discover the Pearl of Africa", color = Color.White, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun MapMarker(
    landmark: Landmark,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // Mapping coordinates to screen simulation
    val xOffset = ((landmark.longitude - 32.0) * 120).dp 
    val yOffset = ((landmark.latitude - 1.0) * -200).dp

    Box(
        modifier = Modifier.offset(x = xOffset, y = yOffset),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
            )
        }
        
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.Place,
                contentDescription = landmark.name,
                tint = if (isSelected) MaterialTheme.colorScheme.primary else Color(0xFFE91E63),
                modifier = Modifier.size(if (isSelected) 42.dp else 32.dp)
            )
        }
    }
}

@Composable
fun UserLocationMarker(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val infiniteTransition = rememberInfiniteTransition(label = "pulse")
        val scale by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 2f,
            animationSpec = infiniteRepeatable(
                animation = tween(1500, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ),
            label = "pulse"
        )
        val alpha by infiniteTransition.animateFloat(
            initialValue = 0.5f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(1500, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ),
            label = "alpha"
        )

        Box(
            modifier = Modifier
                .size(24.dp)
                .graphicsLayer(scaleX = scale, scaleY = scale)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = alpha))
        )
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(2.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
    }
}
