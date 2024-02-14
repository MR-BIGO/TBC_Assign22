package com.example.tbc_assign22.presentation.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import com.example.tbc_assign22.R
import com.example.tbc_assign22.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialItem()
    }

    private fun initialItem() {
        binding.navView.menu.getItem(1).isChecked = true
    }
}