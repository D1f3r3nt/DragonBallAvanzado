package com.keepcoding.dragonballavanzado.data

import com.keepcoding.dragonballavanzado.data.local.LocalDataSource
import com.keepcoding.dragonballavanzado.data.remote.RemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {

    suspend fun login(username: String, password: String): Response<String> {
        return remoteDataSource.login(username, password)
    }
    
    fun getToken(): String? {
        return localDataSource.getToken()
    }

    fun setToken(value: String) {
        localDataSource.setToken(value)
    }
    
}