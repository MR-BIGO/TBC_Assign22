package com.example.tbc_assign22.presentation.screen.messages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tbc_assign22.R
import com.example.tbc_assign22.databinding.FragmentMessagesBinding
import com.example.tbc_assign22.presentation.screen.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessagesFragment : BaseFragment<FragmentMessagesBinding>(FragmentMessagesBinding::inflate) {
    override fun setUp() {
        initialItem()
    }

    private fun initialItem() {
        binding.navView.menu.getItem(2).isChecked = true
    }

}