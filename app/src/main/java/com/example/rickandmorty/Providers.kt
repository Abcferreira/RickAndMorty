package com.example.rickandmorty

import com.example.rickandmorty.data.remote.ApiService
import com.example.rickandmorty.data.repository.CharacterRepository
import com.example.rickandmorty.presentation.characters.viewmodel.CharacterViewModel
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    // OkHttpClient
    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    // Retrofit
    single {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get()) // Usando OkHttpClient injetado
            .build()
    }

    // ApiService
    single {
        get<Retrofit>().create(ApiService::class.java)
    }

    // Repositório
    single { CharacterRepository(get()) }

    // ViewModel
    viewModel { CharacterViewModel(get()) }
}
