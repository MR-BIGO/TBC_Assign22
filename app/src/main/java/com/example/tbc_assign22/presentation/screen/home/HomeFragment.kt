package com.example.tbc_assign22.presentation.screen.home

import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
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
    }

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

         progressBar.visibility =if (state.loading) View.VISIBLE else View.GONE
    }

    private fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeState.collect {
                    handleState(it)
                }
            }
        }
    }
}