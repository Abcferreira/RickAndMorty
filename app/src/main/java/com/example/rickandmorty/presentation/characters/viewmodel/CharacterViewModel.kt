package com.example.rickandmorty.presentation.characters.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.mappers.createCharacterEntity
import com.example.rickandmorty.domain.model.CharacterEntity
import com.example.rickandmorty.domain.repository.ICharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val characterRepository: ICharacterRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _characters = MutableStateFlow(emptyList<CharacterEntity>())
    val characters: StateFlow<List<CharacterEntity>> get() = _characters

    private val _selectedCharacter = MutableStateFlow(createCharacterEntity())
    val selectedCharacter: StateFlow<CharacterEntity?> get() = _selectedCharacter

    private val _hasMorePages = MutableStateFlow(true)
    val hasMorePages: StateFlow<Boolean> = _hasMorePages

    @VisibleForTesting
    internal var currentPage: Int = 1
        private set

    init {
        fetchCharacters()
    }

    fun loadNextPage() {
        if (hasMorePages.value) {
            currentPage++
            fetchCharacters(currentPage)
        }
    }

    fun fetchCharacters(page: Int = 1) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val newCharacters = characterRepository.fetchCharacters(page)

                if (newCharacters.isNotEmpty()) {
                    _characters.value += newCharacters
                } else {
                    _hasMorePages.value = false
                }
            } catch (e: Exception) {
                _hasMorePages.value = false
            } finally {
                _loading.value = false
            }
        }
    }

    fun fetchCharacterById(characterId: Int) {
        viewModelScope.launch {
            _loading.value = true
            val character = characterRepository.getCharacterById(characterId)
            _selectedCharacter.value = character
            _loading.value = false
        }
    }
}
