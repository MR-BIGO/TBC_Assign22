package com.example.tbc_assign22.presentation.mapper

import com.example.tbc_assign22.domain.model.Place
import com.example.tbc_assign22.presentation.model.PlacePresentation

fun Place.toPres(): PlacePresentation {
    return PlacePresentation(
        id = id,
        cover = cover,
        title = title,
    )
}