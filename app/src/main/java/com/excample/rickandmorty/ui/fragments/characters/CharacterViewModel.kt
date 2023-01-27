package com.excample.rickandmorty.ui.fragments.characters

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.excample.rickandmorty.App
import com.excample.rickandmorty.data.models.Characters
import com.excample.rickandmorty.data.repositories.CharacterRepository

class CharacterViewModel : ViewModel() {

    private val repository = CharacterRepository(App.retrofitClient.characterApiService)

    private val _charactersLiveData = MutableLiveData<List<Characters>>()
    val charactersLiveData: LiveData<List<Characters>> = _charactersLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    init {
        getChatacters(page = 1)
    }

    fun getChatacters(page:Int ) {
        repository.getCharacters(
            onSuccess = {
                _charactersLiveData.value = it
            },
            onFailure = {
                _errorLiveData.value = it
            },
            page = page
        )
    }

}