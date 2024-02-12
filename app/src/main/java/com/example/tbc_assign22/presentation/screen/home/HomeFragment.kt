package com.example.tbc_assign22.presentation.screen.home

import android.util.Log.d
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tbc_assign22.databinding.FragmentHomeBinding
import com.example.tbc_assign22.presentation.screen.base.BaseFragment
import com.example.tbc_assign22.presentation.state.home.HomeFragmentState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun setUp() {
        bindObservers()
        viewModel.setUpPlaces()
        viewModel.setUpPosts()
    }

    private fun handleState(state: HomeFragmentState){
        state.places?.let {
            d("checking both in controller", it.toString())
        }

        state.posts?.let {
            d("checking both in controller", it.toString())
        }
    }

    private fun bindObservers(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.homeState.collect{
                    handleState(it)
                }
            }
        }
    }
}