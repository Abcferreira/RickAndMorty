package com.example.rickandmorty.presentation.characters.viewmodel

import app.cash.turbine.test
import com.example.rickandmorty.data.repository.CharacterRepository
import com.example.rickandmorty.domain.model.CharacterEntity
import com.example.rickandmorty.utils.TestDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModelTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    private val characterRepository = mockk<CharacterRepository>()
    private lateinit var viewModel: CharacterViewModel

    private val character1 = CharacterEntity(
        id = 1,
        name = "Rick",
        species = "Human",
        status = "Alive",
        gender = "Male",
        image = "",
        created = "",
        episode = emptyList(),
        location = mockk(),
        origin = mockk(),
        type = "",
        url = ""
    )

    private val character2 = CharacterEntity(
        id = 2,
        name = "Morty",
        species = "Human",
        status = "Alive",
        gender = "Male",
        image = "",
        created = "",
        episode = emptyList(),
        location = mockk(),
        origin = mockk(),
        type = "",
        url = ""
    )

    private val page = 1

    @Before
    fun setup() {
        viewModel = CharacterViewModel(characterRepository)
    }

    @Test
    fun `fetchCharacters should update characters list when repository returns data`() = runTest {
        // Given
        val characters = listOf(character1, character2)
        coEvery { characterRepository.fetchCharacters(page) } returns characters

        // When
        viewModel.fetchCharacters(page)
        advanceUntilIdle()

        // Then
        assertEquals(characters.first(), viewModel.characters.value[0])
        coVerify { characterRepository.fetchCharacters(page) }
    }

    @Test
    fun `fetchCharacters should set hasMorePages to false when no characters returned`() = runTest {
        // Given
        coEvery { characterRepository.fetchCharacters(page) } returns emptyList()

        // When
        viewModel.fetchCharacters(page)
        advanceUntilIdle()

        // Then
        assertFalse(viewModel.hasMorePages.first())
        coVerify { characterRepository.fetchCharacters(page) }
    }

    @Test
    fun `fetchCharacters should handle errors and set hasMorePages to false`() = runTest {
        // Given
        coEvery { characterRepository.fetchCharacters(page) } throws Exception("Network Error")

        // When
        viewModel.fetchCharacters(page)
        advanceUntilIdle()

        // Then
        assertFalse(viewModel.hasMorePages.first())
        coVerify { characterRepository.fetchCharacters(page) }
    }

    @Test
    fun `fetchCharacterById should update selectedCharacter`() = runTest {
        // Given
        coEvery { characterRepository.getCharacterById(page) } returns character1

        // When
        viewModel.fetchCharacterById(page)
        advanceUntilIdle()

        // Then
        assertEquals(character1, viewModel.selectedCharacter.first())
        coVerify(exactly = 1) { characterRepository.getCharacterById(page) }
    }

    @Test
    fun `loading state should be updated correctly`() = runTest {
        // Given
        coEvery { characterRepository.fetchCharacters(page) } returns listOf(character1)

        // When & Then
        viewModel.loading.test {
            assertFalse(awaitItem())

            viewModel.fetchCharacters(page)

            assertTrue(awaitItem())

            advanceUntilIdle()

            assertFalse(awaitItem())
        }

        coVerify { characterRepository.fetchCharacters(page) }
    }
}