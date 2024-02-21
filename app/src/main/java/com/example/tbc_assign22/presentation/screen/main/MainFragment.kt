package com.example.tbc_assign22.presentation.screen.main

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.tbc_assign22.R
import com.example.tbc_assign22.databinding.FragmentMainBinding
import com.example.tbc_assign22.presentation.screen.base.BaseFragment


class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    override fun setUp() {
        val bottomNavigationView = binding.navView
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }
}