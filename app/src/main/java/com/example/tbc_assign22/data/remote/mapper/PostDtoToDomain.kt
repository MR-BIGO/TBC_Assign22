package com.example.tbc_assign22.data.remote.mapper

import com.example.tbc_assign22.data.remote.model.PostDto
import com.example.tbc_assign22.domain.model.Post

fun PostDto.toDomain(): Post {
    return Post(
        id = id,
        comments = comments,
        title = title,
        images = images,
        likes = likes,
        shareContent = shareContent,
        owner = owner.toDomain()
    )
}