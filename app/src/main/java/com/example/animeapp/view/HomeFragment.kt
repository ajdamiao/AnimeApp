package com.example.animeapp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeapp.R
import com.example.animeapp.databinding.FragmentHomeBinding
import com.example.animeapp.model.Anime
import com.example.animeapp.view.adapter.HomeAnimeAdapter
import com.example.animeapp.view.adapter.SearchAnimeAdapter
import com.example.animeapp.viewmodel.HomeViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding : FragmentHomeBinding
    private var homeViewModel = HomeViewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = ""


        homeViewModel.getAnime()
        listenSearchTextChange()

        getAnimes(1, "")

    }

    private fun searchAnime(animeName: String) {
        getAnimes(2, animeName)
    }

    private fun getAnimes(option: Int, animeName: String) {
        homeViewModel.getAnimeResponse().observe(viewLifecycleOwner, { response ->
            when(response) {
                is Anime -> {
                    if (option == 1) {
                        setupAnimeRecyclerView(response,1, "")
                    }
                    else if(option == 2) {

                        setupAnimeRecyclerView(response,2, animeName)
                    }
                    binding.progressBar.visibility = View.GONE
                    binding.rviewAnime.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setupAnimeRecyclerView(response: Anime, option:Int, animeName: String) {
        val recycler = binding.rviewAnime
        recycler.layoutManager = GridLayoutManager(requireContext(), 3)
        if(option == 1) {
            binding.rviewAnime.adapter = HomeAnimeAdapter(response)
        }
        else if (option == 2) {
            binding.rviewAnime.adapter = SearchAnimeAdapter(response, animeName)
        }
    }

    private fun listenSearchTextChange() {
        val editInputs = arrayListOf(binding.inputSearchBar)

        for (editInput in editInputs) {
            editInput.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val animeName = binding.inputSearchBar.text.toString().trim()

                    searchAnime(animeName)

                    if(animeName.isEmpty()) {
                        getAnimes(1,"")
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    val animeName = binding.inputSearchBar.text.toString().trim()

                    searchAnime(animeName)

                    if(animeName.isEmpty()) {
                        getAnimes(1,"")
                    }
                }
            })
        }
    }
}