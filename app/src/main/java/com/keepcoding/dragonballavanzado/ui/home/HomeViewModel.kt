package com.keepcoding.dragonballavanzado.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.dragonballavanzado.data.Repository
import com.keepcoding.dragonballavanzado.models.HeroUI
import com.keepcoding.dragonballavanzado.models.commons.ErrorState
import com.keepcoding.dragonballavanzado.models.commons.Idle
import com.keepcoding.dragonballavanzado.models.commons.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _heros = MutableStateFlow<List<HeroUI>>(emptyList())
    private val _state = MutableStateFlow<State>(Idle())

    val heros: StateFlow<List<HeroUI>> = _heros
    val state: StateFlow<State> = _state

    fun getHeroList() {
        viewModelScope.launch {
            val heros = withContext(Dispatchers.IO) {
                repository.getHeros()
            }

            if (heros.isEmpty()) {
                _state.value = ErrorState("Response is empty")
            }
            
            _heros.value = heros
        }
    }
}