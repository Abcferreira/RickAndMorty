package com.example.rickandmorty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OriginEntity(
    val name: String,
    val url: String
) : Parcelable
