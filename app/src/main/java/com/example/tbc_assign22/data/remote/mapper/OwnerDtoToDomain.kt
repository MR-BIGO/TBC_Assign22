package com.example.tbc_assign22.data.remote.mapper

import com.example.tbc_assign22.data.remote.model.OwnerDto
import com.example.tbc_assign22.domain.model.Owner

fun OwnerDto.toDomain(): Owner {
    return Owner(
        firstName = firstName ?: "",
        lastName = lastName ?: "",
        profile = profile ?: "",
        postDate = postDate ?: 0,
    )
}