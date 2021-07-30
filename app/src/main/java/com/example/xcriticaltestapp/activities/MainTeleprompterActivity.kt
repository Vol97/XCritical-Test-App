package com.example.xcriticaltestapp.activities

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xcriticaltestapp.R
import com.example.xcriticaltestapp.databinding.ActivityMainTeleprompterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainTeleprompterActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainTeleprompterBinding
    private lateinit var navigationController: NavController
    private var isLinear = true
    private val recycler by lazy { findViewById<RecyclerView>(R.id.recyclerViewProjects) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTeleprompterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        navigationController = findNavController(R.id.fragmentContainerView)
        binding.bottomNavigationView.setupWithNavController(navigationController)

        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.show()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        supportActionBar?.elevation = 0F
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_toolbar_list)

        initializeListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        when (item.itemId) {
            R.id.showAsGrid -> {
                if (isLinear) {
                    recycler.layoutManager = GridLayoutManager(this, 2)
                    isLinear = false
                } else {
                    recycler.layoutManager = LinearLayoutManager(this)
                    isLinear = true
                }
            }
        }
        return true
    }

    private fun initializeListeners() {
        binding.floatingActionButton.setOnClickListener {
            navigationController.navigate(R.id.createProjectFragment)
        }

        binding.drawerMenuNavView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.aboutApp -> Toast.makeText(
                    applicationContext,
                    "Clicked info", Toast.LENGTH_LONG
                ).show()
                R.id.contactSupport -> Toast.makeText(
                    applicationContext,
                    "Clicked support", Toast.LENGTH_LONG
                ).show()
                R.id.notifications -> Toast.makeText(
                    applicationContext,
                    "Clicked notifications", Toast.LENGTH_LONG
                ).show()
                R.id.settings -> Toast.makeText(
                    applicationContext,
                    "Clicked settings", Toast.LENGTH_LONG
                ).show()
                R.id.exit -> Toast.makeText(
                    applicationContext,
                    "Clicked exit", Toast.LENGTH_LONG
                ).show()
            }
            true
        }

        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.createProjectFragment -> {
                    supportActionBar?.hide()
                }
                R.id.projectsFragment -> {
                    supportActionBar?.show()
                }
                R.id.galleryFragment -> {
                    supportActionBar?.hide()
                }
            }
        }
    }
}