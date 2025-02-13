package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.remote.response.CharacterResponse
import com.example.rickandmorty.data.remote.response.LocationResponse
import com.example.rickandmorty.data.remote.response.OriginResponse
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MappersTest {

    @Test
    fun `should correctly map CharacterResponse to CharacterEntity`() {
        // GIVEN
        val characterResponse = CharacterResponse(
                id = 1,
                name = "Rick Sanchez",
                species = "Human",
                status = "Alive",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                gender = "Male",
                created = "2017-11-04T18:48:46.250Z",
                episode = listOf("https://rickandmortyapi.com/api/episode/1"),
                location = LocationResponse(name = "Citadel of Ricks", url = "https://rickandmortyapi.com/api/location/20"),
                origin = OriginResponse(name = "Earth", url = "https://rickandmortyapi.com/api/location/1"),
                type = "Scientist",
                url = "https://rickandmortyapi.com/api/character/1"
            )

        // WHEN
        val result = characterResponse.mapToDomain()

        // THEN
        assertEquals(1, result.id)
        assertEquals("Rick Sanchez", result.name)
        assertEquals("Human", result.species)
        assertEquals("Alive", result.status)
        assertEquals("https://rickandmortyapi.com/api/character/avatar/1.jpeg", result.image)
        assertEquals("Male", result.gender)
        assertEquals("2017-11-04T18:48:46.250Z", result.created)
        assertEquals(listOf("https://rickandmortyapi.com/api/episode/1"), result.episode)
        assertEquals("Citadel of Ricks", result.location.name)
        assertEquals("https://rickandmortyapi.com/api/location/20", result.location.url)
        assertEquals("Earth", result.origin.name)
        assertEquals("https://rickandmortyapi.com/api/location/1", result.origin.url)
        assertEquals("Scientist", result.type)
        assertEquals("https://rickandmortyapi.com/api/character/1", result.url)
    }

    @Test
    fun `should correctly map list of CharacterResponse to list of CharacterEntity`() {
        // GIVEN
        val characterResponseList = listOf(
            CharacterResponse(
                id = 1,
                name = "Rick Sanchez",
                species = "Human",
                status = "Alive",
                image = "https://image.url/rick.png",
                gender = "Male",
                created = "2017-11-04T18:48:46.250Z",
                episode = listOf("https://rickandmortyapi.com/api/episode/1"),
                location = LocationResponse("Citadel of Ricks", "https://location.url"),
                origin = OriginResponse("Earth", "https://origin.url"),
                type = "Scientist",
                url = "https://rickandmortyapi.com/api/character/1"
            ),
            CharacterResponse(
                id = 2,
                name = "Morty Smith",
                species = "Human",
                status = "Alive",
                image = "https://image.url/morty.png",
                gender = "Male",
                created = "2017-11-04T18:50:00.000Z",
                episode = listOf("https://rickandmortyapi.com/api/episode/2"),
                location = LocationResponse("Earth", "https://location.url"),
                origin = OriginResponse("Earth", "https://origin.url"),
                type = "",
                url = "https://rickandmortyapi.com/api/character/2"
            )
        )

        // WHEN
        val resultList = characterResponseList.mapToDomain()

        // THEN
        assertEquals(2, resultList.size)
        assertEquals("Rick Sanchez", resultList[0].name)
        assertEquals("Morty Smith", resultList[1].name)
    }

    @Test
    fun `should correctly map LocationResponse to LocationEntity`() {
        // GIVEN
        val locationResponse = LocationResponse(
            name = "Citadel of Ricks",
            url = "https://location.url"
        )

        // WHEN
        val result = locationResponse.mapToDomain()

        // THEN
        assertEquals("Citadel of Ricks", result.name)
        assertEquals("https://location.url", result.url)
    }

    @Test
    fun `should correctly map OriginResponse to OriginEntity`() {
        // GIVEN
        val originResponse = OriginResponse(
            name = "Earth",
            url = "https://origin.url"
        )

        // WHEN
        val result = originResponse.mapToDomain()

        // THEN
        assertEquals("Earth", result.name)
        assertEquals("https://origin.url", result.url)
    }
}