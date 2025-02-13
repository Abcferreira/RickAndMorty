package com.example.rickandmorty

import com.example.rickandmorty.data.remote.ApiService
import com.example.rickandmorty.data.repository.CharacterRepository
import com.example.rickandmorty.domain.repository.ICharacterRepository
import com.example.rickandmorty.presentation.characters.viewmodel.CharacterViewModel
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }

    factory <ICharacterRepository> { CharacterRepository(get()) }


    viewModel { CharacterViewModel(get()) }
}
