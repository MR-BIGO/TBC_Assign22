package com.example.tbc_assign22.data.remote.mapper

import com.example.tbc_assign22.data.remote.model.PlaceDto
import com.example.tbc_assign22.domain.model.Place

fun PlaceDto.toDomain(): Place {
    return Place(
        id = id,
        cover = cover ?: "",
        title = title ?: "",
    )
}