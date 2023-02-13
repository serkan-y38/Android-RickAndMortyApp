package com.example.csgoskins.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csgoskins.domain.usecases.CharactersUseCase
import com.example.csgoskins.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val useCase: CharactersUseCase) : ViewModel() {

    private val _state = MutableStateFlow(CharactersStates())
    val state: MutableStateFlow<CharactersStates> = _state

    init {
        getItems()
    }

    private fun getItems() = viewModelScope.launch(Dispatchers.IO) {

        useCase().collect() {

            when (it) {
                is Resource.Success -> _state.value = CharactersStates(success = it.data ?: emptyList())
                is Resource.Loading -> _state.value = CharactersStates(load = true)
                is Resource.Error -> _state.value = CharactersStates(fail = "error")
            }

        }

    }

}