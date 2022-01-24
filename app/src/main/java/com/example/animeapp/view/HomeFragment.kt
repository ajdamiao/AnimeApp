package com.example.animeapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeapp.R
import com.example.animeapp.databinding.FragmentHomeBinding
import com.example.animeapp.model.Anime
import com.example.animeapp.view.adapter.HomeAnimeAdapter
import com.example.animeapp.viewmodel.HomeViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding : FragmentHomeBinding
    private var homeViewModel = HomeViewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        homeViewModel.getAnime()
        getAnimes()
    }

    private fun getAnimes() {
        homeViewModel.getAnimeResponse().observe(viewLifecycleOwner, { response ->
            when(response) {
                is Anime -> {
                    setupAnimeRecyclerView(response)
                }
            }
        })
    }

    private fun setupAnimeRecyclerView(response: Anime) {
        val recycler = binding.rviewAnime
        recycler.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rviewAnime.adapter = HomeAnimeAdapter(response)
    }
}