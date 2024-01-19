package com.keepcoding.dragonballavanzado.data

import com.keepcoding.andoird_class_one.utils.generateRemoteHeroes
import com.keepcoding.dragonballavanzado.data.remote.RemoteDataSource
import com.keepcoding.dragonballavanzado.utils.fake.FakeLocalDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RepostioryTest {

    private lateinit var localDataSource: FakeLocalDataSource
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        localDataSource = FakeLocalDataSource()
        remoteDataSource = RemoteDataSource(mockk())
    }

    @Test
    fun `WHEN local getHeros empty THEN get from remote and save in local`() = runTest {
        val repository = Repository(localDataSource, remoteDataSource)

        val expectedRemote = generateRemoteHeroes(10)

        coEvery { remoteDataSource.getHeros("") } returns expectedRemote

        val actual = repository.getHeros()

        coVerify(exactly = 1) { remoteDataSource.getHeros("") }
        assertEquals(10, actual.size)
    }

    @Test
    fun `WHEN getHeroDetail THEN get from local`() = runTest {
        val repository = Repository(localDataSource, remoteDataSource)
        val id = "id1"
        
        val expectedRemote = generateRemoteHeroes(10)
        coEvery { remoteDataSource.getHeros("") } returns expectedRemote

        repository.getHeros()
        val actual = repository.getHeroDetail(id)

        assertEquals(id, actual.id)
    }
}