package com.keepcoding.dragonballavanzado.data.remote.apis

import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST

interface DragonBallApi {
    
    @POST("/api/auth/login")
    suspend fun login(@Header("Authorization") auth: String): Response<String>
    
}