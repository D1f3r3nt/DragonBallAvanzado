package com.keepcoding.dragonballavanzado.ui

import androidx.lifecycle.ViewModel
import com.keepcoding.dragonballavanzado.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    
    fun isLogged(): Boolean {
        return !repository.getToken().isNullOrBlank()
    }
    
}