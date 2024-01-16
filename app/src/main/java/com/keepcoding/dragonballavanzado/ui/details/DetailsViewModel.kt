package com.keepcoding.dragonballavanzado.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.dragonballavanzado.data.Repository
import com.keepcoding.dragonballavanzado.models.HeroUI
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

    val hero: StateFlow<HeroUI?> = _hero
    
    fun getHeroDetail(id: String) {
        viewModelScope.launch {
            _hero.value = withContext(Dispatchers.IO) {
                repository.getHeroDetail(id)
            }
        }
    }

}