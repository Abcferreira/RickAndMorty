package com.example.rickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _characters = MutableStateFlow(emptyList<CharacterEntity>())
    val characters: StateFlow<List<CharacterEntity>> get() = _characters

    private val _selectedCharacter = MutableStateFlow(createCharacterEntity())
    val selectedCharacter: StateFlow<CharacterEntity> get() = _selectedCharacter

    fun fetchCharacters(page: Int = 1) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val charactersList = characterRepository.fetchCharacters(page)
                _characters.value = charactersList
            } catch (e: Exception) {
                // Handle error
            } finally {
                _loading.value = false
            }
        }
    }

    fun fetchCharacterById(characterId: Int) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val character = characterRepository.getCharacterById(characterId)
                _selectedCharacter.value = character
            } catch (e: Exception) {
                // Handle error
            } finally {
                _loading.value = false
            }
        }
    }
}


