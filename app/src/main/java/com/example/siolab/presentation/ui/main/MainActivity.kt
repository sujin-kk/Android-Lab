package com.example.siolab.presentation.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.siolab.R
import com.example.siolab.databinding.ActivityMainBinding
import com.example.siolab.presentation.common.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    private val navController: NavController by lazy {
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        with(binding.navBottomView) {
            setupWithNavController(navController)

            setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.sendFragment -> {
                        navController.navigate(R.id.sendFragment)
                    }
                    R.id.historyFragment -> {
                        navController.navigate(R.id.historyFragment)
                        // navController.popBackStack()
//                        if (!navController.popBackStack()) {
//                            finish()
//                        }
                    }
                    R.id.benefitFragment -> {
                        navController.navigate(R.id.benefitFragment)
                       // navController.popBackStack()
                    }
                    R.id.serviceCenterFragment -> {
                        navController.navigate(R.id.serviceCenterFragment)
                        // navController.popBackStack()

                    }
                    R.id.allMenuFragment -> {
                        navController.navigate(R.id.allMenuFragment)
                    }
                }
                true
            }
        }
    }

}