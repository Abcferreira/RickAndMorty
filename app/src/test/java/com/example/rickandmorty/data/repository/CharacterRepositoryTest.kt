package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.mappers.mapToDomain
import com.example.rickandmorty.data.remote.ApiService
import com.example.rickandmorty.data.remote.response.ApiResponse
import com.example.rickandmorty.data.remote.response.CharacterResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CharacterRepositoryTest {
    private val apiService: ApiService = mockk<ApiService>(relaxed = true)
    private lateinit var characterRepository: CharacterRepository

    @Before
    fun setUp() {
        characterRepository = CharacterRepository(apiService = apiService)
    }

    @Test
    fun `should return a list of characters when fetchCharacters is called`() = runTest {
        val response = mockk<ApiResponse>(relaxed = true)
        val page = 1

        coEvery { apiService.getCharacters(page) } returns response

        val expected = response.characters.mapToDomain()
        val actual = characterRepository.fetchCharacters(page)

        assertEquals(expected, actual)

        coVerify { apiService.getCharacters(page) }
        confirmVerified(apiService)
    }

    @Test
    fun `should return a character when getCharacterById is called`() = runTest {
        val response = mockk<CharacterResponse>(relaxed = true)
        val characterId = 1

        coEvery { apiService.getCharacterById(characterId) } returns response

        val expected = response.mapToDomain()
        val actual = characterRepository.getCharacterById(characterId)

        assertEquals(expected, actual)

        coVerify { apiService.getCharacterById(characterId) }
        confirmVerified(apiService)
    }

    @Test
    fun `should return a empty list when fetchCharacters is called and response is empty`() =
        runTest {
            val response = mockk<ApiResponse>(relaxed = true)
            val page = 1

            coEvery { apiService.getCharacters(page) } returns response

            val actual = characterRepository.fetchCharacters(page)

            assertTrue(actual.isEmpty())

            coVerify { apiService.getCharacters(page) }
            confirmVerified(apiService)
        }
}