package com.example.pokemon

import androidx.paging.PagingData
import com.example.domain.model.Pokemon
import com.example.presentation.ui.pokemonList.PokemonListViewModelType
import com.example.presentation.ui.state.PokemonListUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf

class FakePokemonListViewModel : PokemonListViewModelType {

    override val uiState: StateFlow<PokemonListUiState> = MutableStateFlow(
        PokemonListUiState.Success(
            PagingData.from(
                listOf(
                    Pokemon(name = "Bulbasaur", url = ""),
                    Pokemon(name = "Charmander", url = "")
                )
            )
        )
    )

    override val pokemonPagingFlow: Flow<PagingData<Pokemon>> = flowOf(
        PagingData.from(
            listOf(
                Pokemon(name = "Bulbasaur", url = ""),
                Pokemon(name = "Charmander", url = "")
            )
        )
    )

    override val navigateToDetail: SharedFlow<String> = MutableSharedFlow()

    override fun onPokemonClick(pokemon: Pokemon) {
        // no-op
    }
}
