package com.keepcoding.dragonballavanzado.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.dragonballavanzado.data.Repository
import com.keepcoding.dragonballavanzado.models.HeroUI
import com.keepcoding.dragonballavanzado.models.LocationUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor (
    private val repository: Repository,
) : ViewModel() {

    private val _hero = MutableStateFlow<HeroUI?>(null)
    private val _locations = MutableStateFlow<List<LocationUI>>(emptyList())

    val hero: StateFlow<HeroUI?> = _hero
    val locations: StateFlow<List<LocationUI>> = _locations
    
    fun getHeroDetail(id: String) {
        viewModelScope.launch {
            _hero.value = withContext(Dispatchers.IO) {
                repository.getHeroDetail(id)
            }
        }
    }
    
    fun getLocations(id: String) {
        viewModelScope.launch {
            _locations.value = withContext(Dispatchers.IO) {
                repository.getLocations(id)
            }
        }
    }
}