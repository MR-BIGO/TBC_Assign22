package com.example.tbc_assign22.presentation.screen.home

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_assign22.data.common.Resource
import com.example.tbc_assign22.domain.use_case.GetPlacesUseCase
import com.example.tbc_assign22.domain.use_case.GetPostsUseCase
import com.example.tbc_assign22.presentation.mapper.toPres
import com.example.tbc_assign22.presentation.state.home.HomeFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getPlaces: GetPlacesUseCase,
    private val getPosts: GetPostsUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeFragmentState())
    val homeState: SharedFlow<HomeFragmentState> = _homeState.asStateFlow()

//    init {
//        setUpPlaces()
//        setUpPosts()
//        d("checking places data", "${_homeState.value.places}")
//        d("checking posts data", "${_homeState.value.posts}")
//
//    }

     fun setUpPlaces() {
        viewModelScope.launch {
            getPlaces().collect {
                when (it) {
                    is Resource.Success -> {
                        _homeState.update { currentState -> currentState.copy(places = it.data.map { place -> place.toPres() }) }
                        d("checking places data", "${_homeState.value.places}")
                    }

                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                }
            }
        }
    }

     fun setUpPosts() {
        viewModelScope.launch {
            getPosts().collect {
                when (it) {
                    is Resource.Success -> {
                        _homeState.update { currentState -> currentState.copy(posts = it.data.map { post -> post.toPres() }) }
                        d("checking posts data", "${_homeState.value.posts}")
                    }

                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                }
            }
        }
    }

}