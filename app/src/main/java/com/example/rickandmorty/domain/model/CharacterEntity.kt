package com.example.rickandmorty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterEntity(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationEntity,
    val name: String,
    val origin: OriginEntity,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) : Parcelable