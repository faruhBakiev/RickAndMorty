package com.excample.rickandmorty.data.repositories

import com.excample.rickandmorty.data.models.Characters
import com.excample.rickandmorty.data.models.RickAndMortyResponse
import com.excample.rickandmorty.data.models.detail.AboutCharacter
import com.excample.rickandmorty.data.remote.api.RickAndMortyApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository(private val rickAndMortyApiService: RickAndMortyApiService) {

    fun getCharacters(
        onSuccess: (CharacterList: List<Characters>) -> Unit,
        onFailure: (message: String) -> Unit,
        page: Int,

        ) {
        rickAndMortyApiService.getCharacters(page = page)
            .enqueue(object : Callback<RickAndMortyResponse> {
                override fun onResponse(
                    call: Call<RickAndMortyResponse>,
                    response: Response<RickAndMortyResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(it.results)
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

    fun getSingleCharacter(
        onSuccess: (about: AboutCharacter ) -> Unit,
        onFailure: (message: String) -> Unit,
        id : Int
    ) {
        rickAndMortyApiService.getSingleCharacter(id = id)
            .enqueue(object : Callback<AboutCharacter> {
                override fun onResponse(
                    call: Call<AboutCharacter>,
                    response: Response<AboutCharacter>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(it)
                        }
                    }
                }

                override fun onFailure(call: Call<AboutCharacter>, t: Throwable) {
                    t.localizedMessage?.let {
                        onFailure(it)
                    }
                }

            })
    }
}