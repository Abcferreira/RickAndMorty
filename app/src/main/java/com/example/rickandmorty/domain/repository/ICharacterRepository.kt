package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.domain.model.CharacterEntity

interface ICharacterRepository {
    suspend fun fetchCharacters(page: Int): List<CharacterEntity>
    suspend fun getCharacterById(characterId: Int): CharacterEntity
}