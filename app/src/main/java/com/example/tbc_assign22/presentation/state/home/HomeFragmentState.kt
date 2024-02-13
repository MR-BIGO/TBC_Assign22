package com.example.tbc_assign22.presentation.state.home

import com.example.tbc_assign22.presentation.model.PlacePresentation
import com.example.tbc_assign22.presentation.model.PostPresentation

data class HomeFragmentState (
    val places: List<PlacePresentation>? = null,
    val posts: List<PostPresentation>? = null,
    val loading: Boolean = false,
    val error: String? = null
)