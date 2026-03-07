package com.example.tourism.ui.landmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tourism.data.model.Landmark
import com.example.tourism.data.repository.LandmarkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LandmarkViewModel(
    private val landmarkRepository: LandmarkRepository
) : ViewModel() {

    private val _landmarks = MutableStateFlow<List<Landmark>>(emptyList())
    val landmarks: StateFlow<List<Landmark>> = _landmarks

    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val filteredLandmarks: StateFlow<List<Landmark>> = combine(
        _landmarks,
        _selectedCategory,
        _searchQuery
    ) { landmarks, category, query ->
        landmarks.filter { landmark ->
            (category == "All" || landmark.category == category) &&
            (query.isBlank() || landmark.name.contains(query, ignoreCase = true) || 
             landmark.location.contains(query, ignoreCase = true))
        }.sortedBy { it.name }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        loadLandmarks()
    }

    private fun loadLandmarks() {
        viewModelScope.launch {
            _landmarks.value = landmarkRepository.getMajorLandmarks().sortedBy { it.name }
        }
    }

    fun setCategory(category: String) {
        _selectedCategory.value = category
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun getCategories(): List<String> {
        return listOf("All") + _landmarks.value.map { it.category }.distinct().sorted()
    }
}
