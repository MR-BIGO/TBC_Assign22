package com.example.tbc_assign22.presentation.screen.home

import android.view.View
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_assign22.R
import com.example.tbc_assign22.databinding.FragmentHomeBinding
import com.example.tbc_assign22.presentation.event.HomeFragmentEvents
import com.example.tbc_assign22.presentation.screen.base.BaseFragment
import com.example.tbc_assign22.presentation.screen.home.adapters.PlacesRecyclerAdapter
import com.example.tbc_assign22.presentation.screen.home.adapters.PostsRecyclerAdapter
import com.example.tbc_assign22.presentation.state.home.HomeFragmentState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var placesRecyclerAdapter: PlacesRecyclerAdapter
    private lateinit var postsRecyclerAdapter: PostsRecyclerAdapter

    override fun setUp() {
        setUpRecyclers()
        bindObservers()

        viewModel.onEvent(HomeFragmentEvents.GetPlaces)
        viewModel.onEvent(HomeFragmentEvents.GetPosts)

        listeners()

        initialItem()
    }

    private fun initialItem() {
        binding.navView.menu.getItem(1).isChecked = true
    }

    private fun listeners() {
        postsRecyclerAdapter.itemOnClick = {
            viewModel.onEvent(HomeFragmentEvents.PostPressed(it))
        }

//        binding.navView.setOnItemSelectedListener { item ->
//            when(item.itemId){
//                R.id.menuFavourites -> {}
//                R.id.menuBell -> {}
//                R.id.menuMessage -> {}
//                R.id.menuHome -> {}
//            }
//
//        }

    }

//    private fun setCurrentFragment(fragment: Fragment) {
//        childFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer, fragment)
//        }
//    }

    private fun setUpRecyclers() = with(binding) {
        placesRecyclerAdapter = PlacesRecyclerAdapter()
        postsRecyclerAdapter = PostsRecyclerAdapter()

        rvPlaces.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = placesRecyclerAdapter
        }

        rvPosts.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = postsRecyclerAdapter
        }
    }

    private fun handleState(state: HomeFragmentState) = with(binding) {
        state.places?.let {
            placesRecyclerAdapter.setData(it)
        }

        state.posts?.let {
            postsRecyclerAdapter.setData(it)
        }

        state.error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(HomeFragmentEvents.ResetError)
        }

        progressBar.visibility = if (state.loading) View.VISIBLE else View.GONE
    }

    private fun handleEvent(event: HomeFragmentViewModel.HomeNavigationEvents) {
        when(event){
            is HomeFragmentViewModel.HomeNavigationEvents.NavigateToDetails -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(event.id))
            }
        }
    }

    private fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.homeState.collect {
                        handleState(it)
                    }
                }
                launch {
                    viewModel.uiEvent.collect {
                        handleEvent(it)
                    }
                }
            }
        }
    }
}