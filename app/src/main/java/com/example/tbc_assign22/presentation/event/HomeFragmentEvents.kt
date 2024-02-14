package com.example.tbc_assign22.presentation.event

sealed class HomeFragmentEvents {
    data object ResetError : HomeFragmentEvents()
    data object GetPosts : HomeFragmentEvents()
    data object GetPlaces : HomeFragmentEvents()
}
