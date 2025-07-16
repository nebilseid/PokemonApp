package com.example.presentation.ui.state

import androidx.paging.PagingData
import com.example.domain.model.Pokemon

sealed class PokemonListUiState {
    object Loading : PokemonListUiState()
    data class Success(val pokemonPagingData: PagingData<Pokemon>) : PokemonListUiState()
    data class Error(val message: String) : PokemonListUiState()
    object Empty : PokemonListUiState()
}
