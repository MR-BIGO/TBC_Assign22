package com.example.tbc_assign22.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_assign22.data.common.Resource
import com.example.tbc_assign22.domain.use_case.GetPostWithIdUseCase
import com.example.tbc_assign22.presentation.event.DetailsFragmentEvents
import com.example.tbc_assign22.presentation.mapper.toPres
import com.example.tbc_assign22.presentation.state.details.DetailsFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(private val getPost: GetPostWithIdUseCase): ViewModel() {

    private val _detailsState = MutableStateFlow(DetailsFragmentState())
    val detailsState: SharedFlow<DetailsFragmentState> = _detailsState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<DetailsNavigationEvents>()
    val uiEvent: SharedFlow<DetailsNavigationEvents> get() = _uiEvent

    fun onEvent(event: DetailsFragmentEvents){
        when(event){
            is DetailsFragmentEvents.BackPressed -> {}
            is DetailsFragmentEvents.ResetError -> {setError(null)}
            is DetailsFragmentEvents.GetPost -> {getPostWithId(event.id)}
        }

    }

    private fun setError(error: String?){
        viewModelScope.launch {
            _detailsState.update { currentState -> currentState.copy(error = error) }
        }
    }

    private fun getPostWithId(id: Int){
        viewModelScope.launch {
            getPost(id).collect{
                when(it){
                    is Resource.Success -> {_detailsState.update { currentState -> currentState.copy(post = it.data.toPres()) }}
                    is Resource.Loading -> {_detailsState.update { currentState -> currentState.copy(loading = it.loading) }}
                    is Resource.Error -> {setError(it.error)}
                }
            }
        }
    }

    sealed class DetailsNavigationEvents{
        data object NavigateToHome: DetailsNavigationEvents()
    }
}