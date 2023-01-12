package com.excample.rickandmorty

import android.app.Application
import com.excample.rickandmorty.data.remote.RetrofitClient

class App : Application() {

    companion object {
        val retrofitClient = RetrofitClient()
    }

    override fun onCreate() {
        super.onCreate()
    }
}