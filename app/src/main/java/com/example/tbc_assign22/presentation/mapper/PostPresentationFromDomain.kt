package com.example.tbc_assign22.presentation.mapper

import com.example.tbc_assign22.domain.model.Post
import com.example.tbc_assign22.presentation.model.PostPresentation

fun Post.toPres(): PostPresentation {
    return PostPresentation(
        id = id,
        images = images,
        title = title,
        comments = comments,
        likes = likes,
        shareContent = shareContent,
        owner = owner.toPres()
    )
}