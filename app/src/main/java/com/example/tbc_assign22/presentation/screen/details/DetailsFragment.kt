package com.example.tbc_assign22.presentation.screen.details

import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tbc_assign22.R
import com.example.tbc_assign22.databinding.FragmentDetailsBinding
import com.example.tbc_assign22.presentation.event.DetailsFragmentEvents
import com.example.tbc_assign22.presentation.extension.epochToDate
import com.example.tbc_assign22.presentation.model.PostPresentation
import com.example.tbc_assign22.presentation.screen.base.BaseFragment
import com.example.tbc_assign22.presentation.screen.details.adapter.ImagesRecyclerAdapter
import com.example.tbc_assign22.presentation.state.details.DetailsFragmentState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val viewModel: DetailsFragmentViewModel by viewModels()
    private lateinit var imagesRecyclerAdapter: ImagesRecyclerAdapter
    private val args: DetailsFragmentArgs by navArgs()
    override fun setUp() {
        viewModel.onEvent(DetailsFragmentEvents.GetPost(args.id))
        setUpRecycler()
        bindObservers()
        listeners()
    }

//    private fun getPostId() {
//        val postId = arguments?.getInt("postId") ?: 0
//        viewModel.onEvent(DetailsFragmentEvents.GetPost(postId))
//    }

    private fun listeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToHomeFragment())
        }
    }

    private fun setUpRecycler() {
        imagesRecyclerAdapter = ImagesRecyclerAdapter()
        binding.rvImages.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = imagesRecyclerAdapter
        }
    }

    private fun handleState(state: DetailsFragmentState) {
        state.post?.let {
            setValues(it)
        }

        state.error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            d("checking this error", it)
            viewModel.onEvent(DetailsFragmentEvents.ResetError)
        }

        binding.progressBar.visibility = if (state.loading) View.VISIBLE else View.GONE
    }

    private fun handleEvent(event: DetailsFragmentViewModel.DetailsNavigationEvents) {
        when (event) {
            is DetailsFragmentViewModel.DetailsNavigationEvents.NavigateToHome -> {}
        }

    }

    private fun setValues(post: PostPresentation) = with(binding) {
        tvName.text = post.owner.firstName.plus(" ").plus(post.owner.lastName)
        tvTitle.text = post.title
        tvDate.epochToDate(post.owner.postDate.toLong())
        Glide.with(requireContext()).load(post.owner.profile)
            .placeholder(R.drawable.ic_launcher_background).into(ivProfile)
        imagesRecyclerAdapter.apply {
            setData(post.images)
            notifyItemRangeInserted(0, post.images.size)
        }
    }

    private fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.detailsState.collect {
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