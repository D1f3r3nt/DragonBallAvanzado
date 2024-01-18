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
    fun `WHEN getHeroList THEN success list`() = runTest {
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

    @After
    fun tearDown() {

    }

}