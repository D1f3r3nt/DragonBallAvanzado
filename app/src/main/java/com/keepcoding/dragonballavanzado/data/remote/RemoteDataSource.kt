package com.keepcoding.dragonballavanzado.data.remote

import com.keepcoding.dragonballavanzado.data.remote.apis.DragonBallApi
import com.keepcoding.dragonballavanzado.data.remote.requests.HeroRequest
import com.keepcoding.dragonballavanzado.models.HeroRemote
import retrofit2.Response
import java.util.Base64
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: DragonBallApi
) {
    
    suspend fun login(username: String, password: String): Response<String> {
        val auth = basicAuth(username, password)

        return api.login(auth)
    }
    
    suspend fun getHeros(token: String): List<HeroRemote> {
        val auth = bearerToken(token)
        
        return api.getHeros(auth, HeroRequest(""))
    }

    private fun basicAuth(username: String, password: String): String {
        val credentials = "$username:$password"
        val base64Credentials = Base64.getEncoder().encodeToString(credentials.toByteArray())
        return "Basic $base64Credentials"
    }

    private fun bearerToken(token: String): String {
        return "Bearer $token"
    }
    
}