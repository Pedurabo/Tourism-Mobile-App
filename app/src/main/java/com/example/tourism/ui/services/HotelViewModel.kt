package com.example.tourism.ui.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tourism.data.model.Hotel
import com.example.tourism.data.repository.HotelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HotelViewModel(private val hotelRepository: HotelRepository) : ViewModel() {
    private val _hotels = MutableStateFlow<List<Hotel>>(emptyList())
    val hotels: StateFlow<List<Hotel>> = _hotels.asStateFlow()

    init {
        loadHotels()
    }

    private fun loadHotels() {
        viewModelScope.launch {
            hotelRepository.getAllHotels().collect {
                _hotels.value = it
            }
        }
    }
}
