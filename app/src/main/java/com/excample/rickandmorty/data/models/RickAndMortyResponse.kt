package com.excample.rickandmorty.data.models

import com.google.gson.annotations.SerializedName

data class RickAndMortyResponse(
    @SerializedName("results")
    val results: List<Characters>?,
    @SerializedName("info")
    val info: Info
)