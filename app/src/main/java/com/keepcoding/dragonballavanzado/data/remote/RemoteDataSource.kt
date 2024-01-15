package com.keepcoding.dragonballavanzado.data.remote

import com.keepcoding.dragonballavanzado.data.remote.apis.DragonBallApi
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

    private fun basicAuth(username: String, password: String): String {
        val credentials = "$username:$password"
        val base64Credentials = Base64.getEncoder().encodeToString(credentials.toByteArray())
        return "Basic $base64Credentials"
    }
    
}