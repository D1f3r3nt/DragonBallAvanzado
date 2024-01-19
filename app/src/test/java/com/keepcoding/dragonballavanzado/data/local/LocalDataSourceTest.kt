package com.keepcoding.dragonballavanzado.data.local

import com.google.common.truth.Truth
import com.keepcoding.andoird_class_one.utils.generateLocalHeroes
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LocalDataSourceTest {

    private lateinit var dao: HeroDAO
    private lateinit var preferencesDatabase: PreferencesDatabase

    @Before
    fun setUp() {
        dao = mockk()
        preferencesDatabase = mockk()
    }

    @Test
    fun `WHEN getHeros THEN success list`() = runTest {
        // Given
        val localDataSource = LocalDataSource(preferencesDatabase, dao)
        val expected = generateLocalHeroes(20)

        every { localDataSource.getHeros() } returns expected

        // When
        val actual = localDataSource.getHeros()

        // Then
        Assert.assertTrue(actual.isNotEmpty())
        Truth.assertThat(actual).containsExactlyElementsIn(expected)
    }

    @Test
    fun `WHEN getHeroDetail THEN success hero`() = runTest {
        // Given
        val localDataSource = LocalDataSource(preferencesDatabase, dao)
        val expected = generateLocalHeroes(1)[0]
        val id = "id1"

        every { localDataSource.getHeroDetail(id) } returns expected

        // When
        val actual = localDataSource.getHeroDetail(id)

        // Then
        Assert.assertEquals(id, actual.id)
    }

    @Test
    fun `WHEN postTogleFavorite THEN success`() = runTest {
        // Given
        val localDataSource = LocalDataSource(preferencesDatabase, dao)
        val id = "id"

        every { localDataSource.postTogleFavorite(id) } returns Unit

        // When
        val actual = localDataSource.postTogleFavorite(id)

        // Then
        Assert.assertNotNull(actual)
    }

    @Test
    fun `WHEN insertHeros THEN success`() = runTest {
        // Given
        val localDataSource = LocalDataSource(preferencesDatabase, dao)
        val heros = generateLocalHeroes(10)

        every { localDataSource.insertHeros(heros) } returns Unit

        // When
        val actual = localDataSource.insertHeros(heros)

        // Then
        Assert.assertNotNull(actual)
    }

    @Test
    fun `WHEN deleteAll THEN success`() = runTest {
        // Given
        val localDataSource = LocalDataSource(preferencesDatabase, dao)

        every { localDataSource.deleteAll() } returns Unit

        // When
        val actual = localDataSource.deleteAll()

        // Then
        Assert.assertNotNull(actual)
    }

    @Test
    fun `WHEN getToken THEN success`() = runTest {
        // Given
        val localDataSource = LocalDataSource(preferencesDatabase, dao)
        val token = "token"

        every { localDataSource.getToken() } returns token

        // When
        val actual = localDataSource.getToken()

        // Then
        Assert.assertEquals(token, actual)
    }

    @Test
    fun `WHEN getToken THEN error string is null`() = runTest {
        // Given
        val localDataSource = LocalDataSource(preferencesDatabase, dao)
        val token = null

        every { localDataSource.getToken() } returns token

        // When
        val actual = localDataSource.getToken()

        // Then
        Assert.assertNull(actual)
    }

    @Test
    fun `WHEN setToken THEN success`() = runTest {
        // Given
        val localDataSource = LocalDataSource(preferencesDatabase, dao)
        val token = "token"

        every { localDataSource.setToken(token) } returns Unit

        // When
        val actual = localDataSource.setToken(token)

        // Then
        Assert.assertNotNull(actual)
    }

    @After
    fun tearDown() {

    }

}