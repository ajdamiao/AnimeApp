package com.example.animeapp.data

import com.example.animeapp.model.Anime
import retrofit2.http.GET

interface AnimeAPI {

    @GET("anime")
    suspend fun getAnime(): Anime

}