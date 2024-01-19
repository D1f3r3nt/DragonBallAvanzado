package com.keepcoding.dragonballavanzado.data.remote

import com.keepcoding.andoird_class_one.utils.MockWebDispatcher
import com.keepcoding.dragonballavanzado.data.remote.apis.DragonBallApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RemoteDataSourceTest {
    
    private lateinit var api: DragonBallApi
    
    @Before
    fun setUp() {
        val mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MockWebDispatcher()
        mockWebServer.start()
        
        val loggerInterceptor = HttpLoggingInterceptor().apply { 
            level = HttpLoggingInterceptor.Level.BODY
        }
        
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggerInterceptor)
            .build()

        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        
        api = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(DragonBallApi::class.java)
    }
    
    @Test
    fun `WHEN getHeros THEN succes list`() = runTest {
        // GIVEN
        val remoteDataSource = RemoteDataSource(api)
        
        // WHEN
        val heroList = remoteDataSource.getHeros("")
        
        // THEN
        Assert.assertTrue(heroList.isNotEmpty())
    }

    @Test
    fun `WHEN getLocations THEN succes list`() = runTest {
        // GIVEN
        val remoteDataSource = RemoteDataSource(api)

        // WHEN
        val locationsList = remoteDataSource.getLocations("", "")

        // THEN
        Assert.assertTrue(locationsList.isNotEmpty())
    }
    
    @After
    fun tearDown() {
        
    }
}