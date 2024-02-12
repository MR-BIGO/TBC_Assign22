package com.example.tbc_assign22.presentation.model

import com.example.tbc_assign22.domain.model.Owner

data class PostPresentation(
    val id: Int,
    val images: List<String>,
    val title: String,
    val comments: Int,
    val likes: Int,
    val shareContent: String,
    val owner: OwnerPresentation
)