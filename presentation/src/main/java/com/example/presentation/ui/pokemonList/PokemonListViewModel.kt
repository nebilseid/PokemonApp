package com.example.presentation.ui.pokemonList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Pokemon
import com.example.domain.usecase.GetPokemonListUseCase
import com.example.domain.util.Result
import com.example.presentation.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Pokemon>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Pokemon>>> = _uiState

    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            when (val result = getPokemonListUseCase.invoke()) {
                is Result.Success -> {
                    val data = result.data
                    if (data is List<*>) {
                        @Suppress("UNCHECKED_CAST")
                        _uiState.value = UiState.Success(data as List<Pokemon>)
                    } else {
                        _uiState.value = UiState.Error("Unexpected data type")
                    }
                }
                is Result.Error -> {
                    _uiState.value = UiState.Error(result.exception.message ?: "Unknown error")
                }
                is Result.Loading -> {
                    _uiState.value = UiState.Loading
                }
            }

        }
    }
}
