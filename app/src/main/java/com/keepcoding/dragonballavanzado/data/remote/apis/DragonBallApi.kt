package com.keepcoding.dragonballavanzado.data.remote.apis

import com.keepcoding.dragonballavanzado.data.remote.requests.HeroRequest
import com.keepcoding.dragonballavanzado.models.HeroRemote
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface DragonBallApi {
    
    @POST("/api/auth/login")
    suspend fun login(
        @Header("Authorization") auth: String
    ): Response<String>

    @POST("/api/heros/all")
    suspend fun getHeros(
        @Header("Authorization") auth: String, 
        @Body herosRequest: HeroRequest
    ): List<HeroRemote>
    
}