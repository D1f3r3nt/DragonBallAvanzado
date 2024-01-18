package com.keepcoding.dragonballavanzado.ui.favorite

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
class FavoriteViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _heros = MutableStateFlow<List<HeroUI>>(emptyList())

    val heros: StateFlow<List<HeroUI>> = _heros

    fun getHeroFavoriteList() {
        viewModelScope.launch {
            val heros = withContext(Dispatchers.IO) {
                repository.getHeros()
            }

            _heros.value = heros.filter { it.favorite }
        }
    }
}