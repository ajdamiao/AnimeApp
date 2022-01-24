package com.example.animeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animeapp.databinding.ActivityMainBinding
import com.example.animeapp.databinding.FragmentHomeBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbarInclude.toolbar)


    }
}