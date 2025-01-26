package com.example.rickandmorty

fun List<CharacterResponse>.mapToDomain() = map { it.mapToDomain() }

fun CharacterResponse.mapToDomain() =
    CharacterEntity(
        id = id,
        name = name,
        species = species,
        status = status,
        image = image,
        gender = gender,
        created = created,
        episode = episode,
        location = location.mapToDomain(),
        origin = origin.mapToDomain(),
        type = type,
        url = url
    )

private fun LocationResponse.mapToDomain() =
    LocationEntity(
        name = name,
        url = url
    )

private fun OriginResponse.mapToDomain() =
    OriginEntity(
        name = name,
        url = url
    )

fun createCharacterEntity() = CharacterEntity(
    id = 0,
    name = "",
    species = "",
    status = "",
    created = "",
    episode = listOf(),
    gender = "",
    image = "",
    location = createLocationEntity(),
    origin = createOriginEntity(),
    type = "",
    url = "",
)

private fun createOriginEntity() = OriginEntity(name = "", url = "")

private fun createLocationEntity() = LocationEntity(name = "", url = "")