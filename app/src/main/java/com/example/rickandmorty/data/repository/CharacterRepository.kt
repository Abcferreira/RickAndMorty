package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.mappers.mapToDomain
import com.example.rickandmorty.data.remote.ApiService
import com.example.rickandmorty.domain.model.CharacterEntity
import com.example.rickandmorty.domain.repository.ICharacterRepository

class CharacterRepository(private val apiService: ApiService): ICharacterRepository {

    override suspend fun fetchCharacters(page: Int): List<CharacterEntity> {
        val response = apiService.getCharacters(page)
        return if (response.characters.isNotEmpty()) {
            response.characters.mapToDomain()
        } else {
            emptyList()
        }
    }

    override suspend fun getCharacterById(characterId: Int): CharacterEntity {
        val response = apiService.getCharacterById(characterId)
        return response.mapToDomain()
    }
}
