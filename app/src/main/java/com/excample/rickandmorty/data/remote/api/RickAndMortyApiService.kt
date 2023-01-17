package com.excample.rickandmorty.data.remote.api

import com.excample.rickandmorty.data.models.RickAndMortyResponse
import com.excample.rickandmorty.data.models.detail.AboutCharacter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {
    @GET("character")
    fun getCharacters(
        @Query("page") page: Int
    ): Call<RickAndMortyResponse>

    @GET("character/{id}")
    fun getSingleCharacter(
        @Path("id") id: Int
    ): Call<AboutCharacter>
}