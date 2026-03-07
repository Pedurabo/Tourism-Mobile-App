package com.example.tourism

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tourism.data.local.TourismDatabase
import com.example.tourism.data.model.User
import com.example.tourism.data.model.UserType
import com.example.tourism.data.model.ServiceType
import com.example.tourism.data.repository.*
import com.example.tourism.ui.admin.DashboardScreen
import com.example.tourism.ui.admin.DashboardViewModel
import com.example.tourism.ui.ai.AiViewModel
import com.example.tourism.ui.ai.ChatScreen
import com.example.tourism.ui.auth.*
import com.example.tourism.ui.booking.*
import com.example.tourism.ui.home.HomeScreen
import com.example.tourism.ui.landmarks.LandmarkDetailScreen
import com.example.tourism.ui.landmarks.LandmarkViewModel
import com.example.tourism.ui.map.MapScreen
import com.example.tourism.ui.map.MapViewModel
import com.example.tourism.ui.notifications.NotificationScreen
import com.example.tourism.ui.notifications.NotificationViewModel
import com.example.tourism.ui.profile.ProfileScreen
import com.example.tourism.ui.profile.ProfileViewModel
import com.example.tourism.ui.services.*
import kotlinx.coroutines.flow.first

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            val context = LocalContext.current
            val database = remember { TourismDatabase.getDatabase(context) }
            
            // Repositories
            val bookingRepository = remember { BookingRepository(database.bookingDao()) }
            val landmarkRepository = remember { LandmarkRepository(database.landmarkDao()) }
            val userRepository = remember { UserRepository(database.userDao()) }
            val paymentRepository = remember { PaymentRepository() }
            val notificationRepository = remember { NotificationRepository(database.notificationDao()) }
            val hotelRepository = remember { HotelRepository(database.hotelDao()) }
            val carRepository = remember { CarRepository(database.carDao()) }
            val flightRepository = remember { FlightRepository(database.flightDao()) }

            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            val authViewModel: AuthViewModel = viewModel { AuthViewModel(userRepository) }
            val authState by authViewModel.authState.collectAsState()

            val profileViewModel: ProfileViewModel = viewModel()
            val notificationViewModel: NotificationViewModel = viewModel { NotificationViewModel(notificationRepository) }
            
            LaunchedEffect(authState) {
                if (authState is AuthState.LoggedIn) {
                    val loggedInUser = (authState as AuthState.LoggedIn).user
                    profileViewModel.updateFromUser(loggedInUser)
                }
            }

            val user by profileViewModel.user.collectAsState()

            val isAuthRoute = currentRoute in listOf("onboarding", "login", "register")

            Scaffold(
                bottomBar = {
                    if (!isAuthRoute) {
                        TourismBottomBar(user, currentRoute, navController)
                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "onboarding",
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable("onboarding") {
                        OnboardingScreen(
                            onFinished = {
                                navController.navigate("login") {
                                    popUpTo("onboarding") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("login") {
                        LoginScreen(
                            viewModel = authViewModel,
                            onNavigateToRegister = { navController.navigate("register") },
                            onLoginSuccess = { 
                                navController.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("register") {
                        RegisterScreen(
                            viewModel = authViewModel,
                            onNavigateToLogin = { navController.popBackStack() },
                            onRegisterSuccess = {
                                navController.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("home") {
                        val viewModel: LandmarkViewModel = viewModel {
                            LandmarkViewModel(landmarkRepository)
                        }
                        HomeScreen(
                            landmarkViewModel = viewModel,
                            onLandmarkClick = { landmark ->
                                navController.navigate("landmark_detail/${landmark.id}")
                            },
                            onNotificationClick = {
                                navController.navigate("notifications")
                            }
                        )
                    }

                    composable("services") {
                        ServicesScreen(
                            onServiceClick = { serviceType ->
                                when (serviceType) {
                                    ServiceType.HOTEL -> navController.navigate("hotel_list")
                                    ServiceType.CAR_HIRE -> navController.navigate("car_list")
                                    ServiceType.FLIGHT -> navController.navigate("flight_list")
                                    ServiceType.AIRPORT_PICKUP -> {
                                        // Specific simple flow for pickup
                                        navController.navigate("booking_pickup")
                                    }
                                    else -> {}
                                }
                            }
                        )
                    }

                    composable("hotel_list") {
                        val viewModel: HotelViewModel = viewModel { HotelViewModel(hotelRepository) }
                        HotelListScreen(
                            viewModel = viewModel,
                            onBackClick = { navController.popBackStack() },
                            onHotelClick = { hotel ->
                                navController.navigate("booking_service/${ServiceType.HOTEL}/${hotel.id}/${hotel.pricePerNight}")
                            }
                        )
                    }

                    composable("car_list") {
                        val viewModel: CarViewModel = viewModel { CarViewModel(carRepository) }
                        CarListScreen(
                            viewModel = viewModel,
                            onBackClick = { navController.popBackStack() },
                            onCarClick = { car ->
                                navController.navigate("booking_service/${ServiceType.CAR_HIRE}/${car.id}/${car.pricePerDay}")
                            }
                        )
                    }

                    composable("flight_list") {
                        val viewModel: FlightViewModel = viewModel { FlightViewModel(flightRepository) }
                        FlightListScreen(
                            viewModel = viewModel,
                            onBackClick = { navController.popBackStack() },
                            onFlightClick = { flight ->
                                navController.navigate("booking_service/${ServiceType.FLIGHT}/${flight.id}/${flight.price}")
                            }
                        )
                    }

                    composable(
                        route = "booking_service/{type}/{id}/{price}",
                        arguments = listOf(
                            navArgument("type") { type = NavType.StringType },
                            navArgument("id") { type = NavType.StringType },
                            navArgument("price") { type = NavType.FloatType }
                        )
                    ) { backStackEntry ->
                        val type = ServiceType.valueOf(backStackEntry.arguments?.getString("type") ?: "HOTEL")
                        val id = backStackEntry.arguments?.getString("id") ?: ""
                        val price = backStackEntry.arguments?.getFloat("price") ?: 0f
                        
                        val viewModel: BookingViewModel = viewModel { BookingViewModel(bookingRepository) }
                        ServiceBookingScreen(
                            serviceId = id,
                            serviceType = type,
                            unitPrice = price.toDouble(),
                            viewModel = viewModel,
                            userId = user.id,
                            onBookingSuccess = {
                                navController.navigate("payment")
                            }
                        )
                    }

                    composable("booking_pickup") {
                        val viewModel: BookingViewModel = viewModel { BookingViewModel(bookingRepository) }
                        ServiceBookingScreen(
                            serviceId = "pickup_entebbe",
                            serviceType = ServiceType.AIRPORT_PICKUP,
                            unitPrice = 30.0, // Fixed price for pickup
                            viewModel = viewModel,
                            userId = user.id,
                            onBookingSuccess = {
                                navController.navigate("payment")
                            }
                        )
                    }

                    composable("notifications") {
                        NotificationScreen(
                            viewModel = notificationViewModel,
                            userId = user.id,
                            onBackClick = { navController.popBackStack() }
                        )
                    }

                    composable("dashboard") {
                        if (user.userType == UserType.ADMIN || user.userType == UserType.TOUR_OPERATOR) {
                            val viewModel: DashboardViewModel = viewModel { 
                                DashboardViewModel(bookingRepository, landmarkRepository, database.userDao())
                            }
                            DashboardScreen(viewModel = viewModel)
                        } else {
                            AccessDeniedScreen(onBack = { navController.navigate("home") })
                        }
                    }

                    composable("ai") {
                        val viewModel: AiViewModel = viewModel()
                        ChatScreen(viewModel = viewModel)
                    }
                    
                    composable(
                        route = "landmark_detail/{landmarkId}",
                        arguments = listOf(navArgument("landmarkId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val landmarkId = backStackEntry.arguments?.getString("landmarkId")
                        var landmark by remember { mutableStateOf<com.example.tourism.data.model.Landmark?>(null) }
                        
                        LaunchedEffect(landmarkId) {
                            landmarkId?.let {
                                landmark = database.landmarkDao().getLandmarkById(it)
                            }
                        }

                        landmark?.let {
                            LandmarkDetailScreen(
                                landmark = it,
                                onBackClick = { navController.popBackStack() },
                                onBookClick = { 
                                    navController.navigate("booking_service/${ServiceType.LANDMARK}/${it.id}/${it.tourPrice.toFloat()}")
                                }
                            )
                        }
                    }

                    composable("map") {
                        val viewModel: MapViewModel = viewModel {
                            MapViewModel(landmarkRepository)
                        }
                        MapScreen(
                            viewModel = viewModel,
                            onLandmarkClick = { landmark ->
                                navController.navigate("landmark_detail/${landmark.id}")
                            }
                        )
                    }

                    composable("my_bookings") {
                        val viewModel: MyBookingsViewModel = viewModel {
                            MyBookingsViewModel(bookingRepository)
                        }
                        LaunchedEffect(Unit) {
                            viewModel.loadUserBookings()
                        }
                        MyBookingsScreen(viewModel = viewModel)
                    }

                    composable("payment") {
                        val viewModel: PaymentViewModel = viewModel {
                            PaymentViewModel(paymentRepository)
                        }
                        
                        var latestBooking by remember { mutableStateOf<com.example.tourism.data.model.Booking?>(null) }
                        LaunchedEffect(Unit) {
                            latestBooking = database.bookingDao().getAllBookings().first().firstOrNull()
                        }

                        latestBooking?.let {
                            PaymentSelectionScreen(
                                booking = it,
                                viewModel = viewModel,
                                onPaymentComplete = { txnRef ->
                                    navController.navigate("my_bookings") {
                                        popUpTo("home") { inclusive = false }
                                    }
                                }
                            )
                        }
                    }

                    composable("profile") {
                        ProfileScreen(
                            viewModel = profileViewModel,
                            onLogout = {
                                authViewModel.logout()
                                navController.navigate("login") {
                                    popUpTo(0) { inclusive = true }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TourismBottomBar(user: User, currentRoute: String?, navController: androidx.navigation.NavHostController) {
    val allItems = listOf("home", "services", "map", "ai", "my_bookings", "dashboard", "profile")
    val allLabels = listOf("Home", "Services", "Map", "AI Guide", "Bookings", "Admin", "Profile")
    val allIcons = listOf(
        Icons.Default.Home, 
        Icons.Default.Category,
        Icons.Default.Place, 
        Icons.Default.AutoAwesome, 
        Icons.Default.ConfirmationNumber, 
        Icons.Default.Dashboard, 
        Icons.Default.Person
    )

    val filteredIndices = allItems.indices.filter { index ->
        val route = allItems[index]
        when (user.userType) {
            UserType.ADMIN -> true
            UserType.TOUR_OPERATOR -> route in listOf("home", "services", "dashboard", "profile")
            UserType.TOURIST -> route != "dashboard"
        }
    }

    NavigationBar {
        filteredIndices.forEach { index ->
            val route = allItems[index]
            NavigationBarItem(
                icon = { Icon(allIcons[index], contentDescription = allLabels[index]) },
                label = { Text(allLabels[index], fontSize = 10.sp) },
                selected = currentRoute == route,
                onClick = {
                    if (currentRoute != route) {
                        navController.navigate(route) {
                            popUpTo("home") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun AccessDeniedScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.Lock, 
            contentDescription = null, 
            modifier = Modifier.size(100.dp), 
            tint = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text("Access Denied", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text(
            "You do not have permission to view the admin dashboard.", 
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onBack, shape = RoundedCornerShape(12.dp)) {
            Text("Go Back Home")
        }
    }
}
