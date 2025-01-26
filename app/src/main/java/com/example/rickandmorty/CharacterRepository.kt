package com.example.rickandmorty

class CharacterRepository(private val apiService: ApiService): ICharacterRepository {

    override suspend fun fetchCharacters(page: Int): List<CharacterEntity> {
        val response = apiService.getCharacters(page)
        return if (response.characters.isNotEmpty()) {
            val charactersList = response.characters.mapToDomain()
            charactersList
        } else {
            emptyList()
        }
    }

    override suspend fun getCharacterById(characterId: Int): CharacterEntity {
        val response = apiService.getCharacterById(characterId)
        return response.mapToDomain()
    }
}
