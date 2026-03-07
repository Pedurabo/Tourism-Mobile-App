package com.example.tourism.ui.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tourism.data.local.UserDao
import com.example.tourism.data.model.Booking
import com.example.tourism.data.model.BookingStatus
import com.example.tourism.data.model.Landmark
import com.example.tourism.data.model.User
import com.example.tourism.data.model.UserType
import com.example.tourism.data.repository.BookingRepository
import com.example.tourism.data.repository.LandmarkRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class DashboardViewModel(
    private val bookingRepository: BookingRepository,
    private val landmarkRepository: LandmarkRepository,
    private val userDao: UserDao
) : ViewModel() {

    private val _dashboardState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val dashboardState: StateFlow<DashboardUiState> = _dashboardState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _bookingStatusFilter = MutableStateFlow<BookingStatus?>(null)
    val bookingStatusFilter: StateFlow<BookingStatus?> = _bookingStatusFilter

    private val _uiEvent = Channel<DashboardUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        loadDashboardData()
    }

    fun loadDashboardData() {
        viewModelScope.launch {
            _dashboardState.value = DashboardUiState.Loading
            try {
                combine(
                    bookingRepository.getAllBookings(),
                    landmarkRepository.getAllLandmarks(),
                    userDao.getAllUsers(),
                    _searchQuery,
                    _bookingStatusFilter
                ) { bookings, landmarks, users, query, statusFilter ->
                    val totalRevenue = bookings.filter { it.status != BookingStatus.CANCELLED }.sumOf { it.totalAmount }
                    val activeTours = bookings.count { it.status == BookingStatus.CONFIRMED }
                    val pendingBookings = bookings.count { it.status == BookingStatus.PENDING }

                    val filteredBookings = bookings.filter { booking ->
                        statusFilter == null || booking.status == statusFilter
                    }.reversed()

                    val filteredLandmarks = landmarks.filter { landmark ->
                        landmark.name.contains(query, ignoreCase = true) ||
                        landmark.location.contains(query, ignoreCase = true) ||
                        landmark.category.contains(query, ignoreCase = true)
                    }
                    
                    val filteredUsers = users.filter { user ->
                        user.fullName.contains(query, ignoreCase = true) ||
                        user.email.contains(query, ignoreCase = true)
                    }

                    DashboardUiState.Success(
                        bookings = filteredBookings,
                        landmarks = filteredLandmarks,
                        users = filteredUsers,
                        totalRevenue = totalRevenue,
                        activeTours = activeTours,
                        pendingBookings = pendingBookings
                    )
                }.collect { state ->
                    _dashboardState.value = state
                }
            } catch (e: Exception) {
                _dashboardState.value = DashboardUiState.Error(e.message ?: "Failed to load dashboard")
            }
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setBookingStatusFilter(status: BookingStatus?) {
        _bookingStatusFilter.value = status
    }

    fun updateBookingStatus(bookingId: String, newStatus: BookingStatus) {
        viewModelScope.launch {
            try {
                bookingRepository.updateBookingStatus(bookingId, newStatus)
                _uiEvent.send(DashboardUiEvent.ShowSnackbar("Booking status updated to ${newStatus.name}"))
            } catch (e: Exception) {
                _uiEvent.send(DashboardUiEvent.ShowSnackbar("Error: ${e.message}"))
            }
        }
    }

    fun deleteLandmark(landmark: Landmark) {
        viewModelScope.launch {
            try {
                landmarkRepository.deleteLandmark(landmark)
                _uiEvent.send(DashboardUiEvent.ShowSnackbar("${landmark.name} deleted successfully"))
            } catch (e: Exception) {
                _uiEvent.send(DashboardUiEvent.ShowSnackbar("Error: ${e.message}"))
            }
        }
    }

    fun addTourismSite(
        name: String,
        location: String,
        description: String,
        category: String,
        imageUrl: String,
        price: Double,
        lat: Double = 0.0,
        long: Double = 0.0
    ) {
        viewModelScope.launch {
            try {
                val newLandmark = Landmark(
                    id = UUID.randomUUID().toString(),
                    name = name,
                    location = location,
                    description = description,
                    category = category,
                    imageUrl = imageUrl,
                    latitude = lat,
                    longitude = long,
                    tourPrice = price
                )
                landmarkRepository.addLandmark(newLandmark)
                _uiEvent.send(DashboardUiEvent.ShowSnackbar("$name added successfully"))
            } catch (e: Exception) {
                _uiEvent.send(DashboardUiEvent.ShowSnackbar("Error: ${e.message}"))
            }
        }
    }

    fun updateLandmark(landmark: Landmark) {
        viewModelScope.launch {
            try {
                landmarkRepository.addLandmark(landmark)
                _uiEvent.send(DashboardUiEvent.ShowSnackbar("${landmark.name} updated successfully"))
            } catch (e: Exception) {
                _uiEvent.send(DashboardUiEvent.ShowSnackbar("Error: ${e.message}"))
            }
        }
    }

    fun updateUserRole(user: User, newRole: UserType) {
        viewModelScope.launch {
            try {
                userDao.updateUser(user.copy(userType = newRole))
                _uiEvent.send(DashboardUiEvent.ShowSnackbar("User ${user.fullName} role updated to $newRole"))
            } catch (e: Exception) {
                _uiEvent.send(DashboardUiEvent.ShowSnackbar("Error: ${e.message}"))
            }
        }
    }

    fun exportReport() {
        viewModelScope.launch {
            _uiEvent.send(DashboardUiEvent.ShowSnackbar("Generating travel services report..."))
            kotlinx.coroutines.delay(2000)
            _uiEvent.send(DashboardUiEvent.ShowSnackbar("Report exported: Tourism_Services_2025.csv"))
        }
    }
}

sealed class DashboardUiState {
    object Loading : DashboardUiState()
    data class Success(
        val bookings: List<Booking>,
        val landmarks: List<Landmark>,
        val users: List<User>,
        val totalRevenue: Double,
        val activeTours: Int,
        val pendingBookings: Int
    ) : DashboardUiState()
    data class Error(val message: String) : DashboardUiState()
}

sealed class DashboardUiEvent {
    data class ShowSnackbar(val message: String) : DashboardUiEvent()
}
