package com.example.tbc_assign22.presentation.event

sealed class DetailsFragmentEvents{
    data object BackPressed: DetailsFragmentEvents()
    data object ResetError: DetailsFragmentEvents()
    data class GetPost(val id: Int): DetailsFragmentEvents()
}
