package com.keepcoding.dragonballavanzado.data.remote.apis

import com.keepcoding.dragonballavanzado.data.remote.requests.HeroRequest
import com.keepcoding.dragonballavanzado.data.remote.requests.LocationRequest
import com.keepcoding.dragonballavanzado.models.HeroRemote
import com.keepcoding.dragonballavanzado.models.LocationRemote
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

    @POST("/api/heros/locations")
    suspend fun getLocations(
        @Header("Authorization") auth: String,
        @Body locationRequest: LocationRequest
    ): List<LocationRemote>

}