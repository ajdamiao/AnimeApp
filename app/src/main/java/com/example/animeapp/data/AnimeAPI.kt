package com.example.animeapp.data

import com.example.animeapp.model.Anime
import com.example.animeapp.model.Data
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeAPI {

    @GET("anime")
    suspend fun getAnime(): Anime

    @GET("anime/{id}")
    suspend fun getAnimeById(
        @Path("id") id: Int,
    ): Data

}