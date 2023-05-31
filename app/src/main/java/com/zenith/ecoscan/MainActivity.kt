package com.zenith.ecoscan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.zenith.ecoscan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private var currentDestination : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_nav_host) as NavHostFragment
        navController = navHostFragment.navController

        val showNavbarFragment = setOf(R.id.homeFragment, R.id.formFragment)

        navController.addOnDestinationChangedListener {  _, destination, _ ->
            currentDestination = destination.id
            if (destination.id in showNavbarFragment) {
                binding.bottomNavigation.visibility = View.VISIBLE
            } else {
                binding.bottomNavigation.visibility = View.GONE
            }
        }

        binding.bottomNavigation.setupWithNavController(navController)

        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    if (currentDestination != R.id.homeFragment) {
                        navController.navigate(R.id.homeFragment)
                    }
                }
                R.id.menu_form -> {
                    if (currentDestination != R.id.formFragment) {
                        navController.navigate(R.id.formFragment)
                    }
                }
            }
            true
        }
    }
}