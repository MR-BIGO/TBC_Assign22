package com.example.tbc_assign22.data.remote.mapper

import com.example.tbc_assign22.data.remote.model.PostDto
import com.example.tbc_assign22.domain.model.Owner
import com.example.tbc_assign22.domain.model.Post

fun PostDto.toDomain(): Post {
    return Post(
        id = id,
        comments = comments ?: 0,
        title = title ?: "",
        images = images ?: listOf(),
        likes = likes ?: 0,
        shareContent = shareContent ?: "",
        owner = owner?.toDomain() ?: Owner("", "", "", 0)
    )
}