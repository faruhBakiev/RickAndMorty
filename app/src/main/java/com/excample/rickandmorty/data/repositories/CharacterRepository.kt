package com.excample.rickandmorty.data.repositories

import com.excample.rickandmorty.data.models.Characters
import com.excample.rickandmorty.data.models.RickAndMortyResponse
import com.excample.rickandmorty.data.remote.api.RickAndMortyApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository(private val rickAndMortyApiService: RickAndMortyApiService) {

    fun getCharacters(
        onSuccess: (CharacterList: List<Characters>) -> Unit,
        onFailure: (message: String) -> Unit,
    ) {
        rickAndMortyApiService.getCharacters().enqueue(object : Callback<RickAndMortyResponse> {
            override fun onResponse(
                call: Call<RickAndMortyResponse>,
                response: Response<RickAndMortyResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it.results!!)
                    }
                }
            }

            override fun onFailure(call: Call<RickAndMortyResponse>, t: Throwable) {
                t.localizedMessage?.let {
                    onFailure(it)
                }
            }
        })
    }
}