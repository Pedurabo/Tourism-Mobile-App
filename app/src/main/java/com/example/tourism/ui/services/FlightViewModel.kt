package com.example.tourism.ui.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tourism.data.model.Flight
import com.example.tourism.data.repository.FlightRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FlightViewModel(private val flightRepository: FlightRepository) : ViewModel() {
    private val _flights = MutableStateFlow<List<Flight>>(emptyList())
    val flights: StateFlow<List<Flight>> = _flights.asStateFlow()

    init {
        loadFlights()
    }

    private fun loadFlights() {
        viewModelScope.launch {
            flightRepository.getAllFlights().collect {
                _flights.value = it
            }
        }
    }
}
