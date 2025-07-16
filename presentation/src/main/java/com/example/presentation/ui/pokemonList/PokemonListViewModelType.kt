package com.example.presentation.ui.pokemonList

import androidx.paging.PagingData
import com.example.domain.model.Pokemon
import com.example.presentation.ui.state.PokemonListUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface PokemonListViewModelType {
    val uiState: StateFlow<PokemonListUiState>
    val pokemonPagingFlow: Flow<PagingData<Pokemon>>
    val navigateToDetail: SharedFlow<String>
    fun onPokemonClick(pokemon: Pokemon)
}
