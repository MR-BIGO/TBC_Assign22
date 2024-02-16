package com.example.tbc_assign22.presentation.state.details

import com.example.tbc_assign22.presentation.model.PostPresentation

data class DetailsFragmentState(
    val post: PostPresentation? = null,
    val loading: Boolean = false,
    val error: String? = null
)
