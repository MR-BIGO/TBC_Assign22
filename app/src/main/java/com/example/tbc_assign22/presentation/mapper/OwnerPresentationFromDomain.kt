package com.example.tbc_assign22.presentation.mapper

import com.example.tbc_assign22.domain.model.Owner
import com.example.tbc_assign22.presentation.model.OwnerPresentation

fun Owner.toPres(): OwnerPresentation {
    return OwnerPresentation(
        firstName = firstName,
        lastName = lastName,
        profile = profile,
        postDate = postDate,
    )
}