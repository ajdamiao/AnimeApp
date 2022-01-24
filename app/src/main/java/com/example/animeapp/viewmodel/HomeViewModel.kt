package com.example.animeapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.animeapp.data.repository.AnimeRepository
import com.example.animeapp.model.Anime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel {
    private val apiRepository = AnimeRepository().makeRequest()
    private val animeResponse: MutableLiveData<Anime> = MutableLiveData()

    fun getAnimeResponse() : MutableLiveData<Anime> {
        return animeResponse
    }

    fun getAnime() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val animes = apiRepository.getAnime()

                animeResponse.postValue(animes)
            } catch (e: Exception) {
                println("ERROR: $e")
            }
        }
    }
}