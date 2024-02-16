package com.example.tbc_assign22.presentation.screen.notifications

import com.example.tbc_assign22.databinding.FragmentNotificationsBinding
import com.example.tbc_assign22.presentation.screen.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : BaseFragment<FragmentNotificationsBinding>(FragmentNotificationsBinding::inflate) {
    override fun setUp() {
        initialItem()
    }

    private fun initialItem() {
        binding.navView.menu.getItem(3).isChecked = true
    }
}