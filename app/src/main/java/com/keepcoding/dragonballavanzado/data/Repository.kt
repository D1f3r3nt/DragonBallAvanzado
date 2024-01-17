package com.keepcoding.dragonballavanzado.data

import com.keepcoding.dragonballavanzado.data.local.LocalDataSource
import com.keepcoding.dragonballavanzado.data.remote.RemoteDataSource
import com.keepcoding.dragonballavanzado.models.HeroLocal
import com.keepcoding.dragonballavanzado.models.HeroRemote
import com.keepcoding.dragonballavanzado.models.HeroUI
import com.keepcoding.dragonballavanzado.models.LocationRemote
import com.keepcoding.dragonballavanzado.models.LocationUI
import com.keepcoding.dragonballavanzado.models.mapToLocal
import com.keepcoding.dragonballavanzado.models.mapToUI
import okhttp3.internal.EMPTY_RESPONSE
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

        // Borrar BDD
        localDataSource.deleteAll()
        
        // Get from network
        var remoteHeros: List<HeroRemote> = emptyList()
        token?.let {
            remoteHeros = remoteDataSource.getHeros(it)
        }

        // Save in BDD
        localDataSource.insertHeros(remoteHeros.map { it.mapToLocal() })

        // Get from BDD
        val updateLocalHeros: List<HeroLocal> = localDataSource.getHeros()
        return updateLocalHeros.map { it.mapToUI() }
    }
    
    suspend fun getLocations(heroID: String): List<LocationUI> {
        val token = getToken()
        
        var locations: List<LocationRemote> = emptyList()
        token?.let {
            locations = remoteDataSource.getLocations(token, heroID)
        }
        
        return locations.map { it.mapToUI() }
    }

    suspend fun postTogleFavorite(heroID: String): Response<Unit> {
        val token = getToken()

        // Remote
        var response: Response<Unit> = Response.error(404, EMPTY_RESPONSE)
        token?.let {
            response = remoteDataSource.postTogleFavorite(token, heroID)
        }
        
        // Local
        if (response.code() == 201) {
            localDataSource.postTogleFavorite(heroID)
        }

        return response
    }

    suspend fun getHeroDetail(id: String): HeroUI {
        val heroLocal = localDataSource.getHeroDetail(id)

        return heroLocal.mapToUI()
    }
    
    fun getToken(): String? {
        return localDataSource.getToken()
    }

    fun setToken(value: String) {
        localDataSource.setToken(value)
    }
    
}