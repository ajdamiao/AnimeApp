package com.example.animeapp.data.repository

import com.example.animeapp.data.AnimeAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AnimeRepository {

    private val baseUrl = "https://api.jikan.moe/v4/"

    fun makeRequest(): AnimeAPI {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeAPI::class.java)
    }
}