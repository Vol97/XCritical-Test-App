package com.example.xcriticaltestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import androidx.navigation.ui.setupWithNavController
import com.example.xcriticaltestapp.databinding.ActivityMainTeleprompterBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainTeleprompterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainTeleprompterBinding
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTeleprompterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initializeListeners()
        navigationController = findNavController(R.id.fragmentContainerView)
        binding.bottomNavigationView.setupWithNavController(navigationController)
    }

    private fun initializeListeners() {
        binding.floatingActionButton.setOnClickListener {
            navigationController.navigate(R.id.createProjectFragment)
        }
    }
}