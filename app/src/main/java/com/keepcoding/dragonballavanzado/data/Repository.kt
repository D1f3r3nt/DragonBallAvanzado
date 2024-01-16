package com.keepcoding.dragonballavanzado.data

import com.keepcoding.dragonballavanzado.data.local.LocalDataSource
import com.keepcoding.dragonballavanzado.data.remote.RemoteDataSource
import com.keepcoding.dragonballavanzado.models.HeroUI
import com.keepcoding.dragonballavanzado.models.mapToUI
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {
    
    suspend fun login(username: String, password: String): Response<String> {
        return remoteDataSource.login(username, password)
    }
    
    suspend fun getHeros(): List<HeroUI> {
        val token = getToken()
        
        var herosResponse = emptyList<HeroUI>()
        
        token?.let { 
            val heros = remoteDataSource.getHeros(it)
            herosResponse = heros.map { hero -> hero.mapToUI() }
        }
        
        return herosResponse
    }
    
    fun getToken(): String? {
        return localDataSource.getToken()
    }

    fun setToken(value: String) {
        localDataSource.setToken(value)
    }
    
}