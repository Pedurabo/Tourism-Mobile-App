package com.example.tourism.ui.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tourism.data.model.Landmark
import com.example.tourism.data.repository.LandmarkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MapViewModel(
    private val landmarkRepository: LandmarkRepository
) : ViewModel() {

    private val _landmarks = MutableStateFlow<List<Landmark>>(emptyList())
    val landmarks: StateFlow<List<Landmark>> = _landmarks

    init {
        loadLandmarks()
    }

    private fun loadLandmarks() {
        viewModelScope.launch {
            _landmarks.value = landmarkRepository.getMajorLandmarks()
        }
    }
}
