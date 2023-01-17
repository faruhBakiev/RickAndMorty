package com.excample.rickandmorty.ui.fragments.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.excample.rickandmorty.App
import com.excample.rickandmorty.data.models.detail.AboutCharacter
import com.excample.rickandmorty.data.repositories.CharacterRepository

class DetailViewModel : ViewModel() {

    private val repository = CharacterRepository(App.retrofitClient.characterApiService)

    private val _aboutCharactersLiveData = MutableLiveData<AboutCharacter>()
    val aboutCharactersLiveData: LiveData<AboutCharacter> = _aboutCharactersLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun getSingleCharacter(id: Int) {
        repository.getSingleCharacter(
            onSuccess = {
                _aboutCharactersLiveData.value = it
            },
            onFailure = {
                _errorLiveData.value = it
            },
            id = id
        )
    }
}