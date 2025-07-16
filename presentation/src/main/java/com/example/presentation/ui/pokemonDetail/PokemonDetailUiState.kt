package com.example.presentation.ui.pokemonDetail

import com.example.domain.model.PokemonDetail

sealed class PokemonDetailUiState {
    object Loading : PokemonDetailUiState()
    data class Success(val pokemonDetail: PokemonDetail) : PokemonDetailUiState()
    data class Error(val message: String) : PokemonDetailUiState()
}
