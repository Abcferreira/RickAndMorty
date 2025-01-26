package com.example.rickandmorty

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): ApiResponse

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") characterId: Int): CharacterResponse
}
