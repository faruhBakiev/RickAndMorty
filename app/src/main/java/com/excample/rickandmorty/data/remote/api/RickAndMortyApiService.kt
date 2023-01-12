package com.excample.rickandmorty.data.remote.api

import com.excample.rickandmorty.data.models.RickAndMortyResponse
import retrofit2.Call
import retrofit2.http.GET

interface RickAndMortyApiService {
    @GET("character")
    fun getCharacters(): Call<RickAndMortyResponse>
}