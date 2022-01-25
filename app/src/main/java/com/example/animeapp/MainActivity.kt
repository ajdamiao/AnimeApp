package com.example.animeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.animeapp.databinding.ActivityMainBinding
import com.example.animeapp.databinding.FragmentHomeBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbarInclude.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContent) as NavHostFragment
        navController = navHostFragment.navController

        setBackButtonVisibility()

        binding.toolbarInclude.btnBack.setOnClickListener {
            navController.popBackStack()
        }

    }

    private fun setBackButtonVisibility() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if(destination.id == R.id.homeFragment) {
                binding.toolbarInclude.btnBack.visibility = View.GONE
            }
            else {
                binding.toolbarInclude.btnBack.visibility = View.VISIBLE
            }
        }
    }
}