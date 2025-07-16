package com.example.presentation.ui.pokemonList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.domain.model.Pokemon
import com.example.presentation.ui.state.PokemonListUiState

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel(),
    onPokemonClick: (String) -> Unit,
    contentPadding: PaddingValues
) {
    val uiState by viewModel.uiState.collectAsState()

    val lazyPagingItems: LazyPagingItems<Pokemon> =
        viewModel.pokemonPagingFlow.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        when (uiState) {
            is PokemonListUiState.Loading -> LoadingScreen()
            is PokemonListUiState.Error -> ErrorScreen(
                message = (uiState as PokemonListUiState.Error).message,
                onRetry = { /* handle retry */ }
            )

            is PokemonListUiState.Empty -> EmptyScreen()
            is PokemonListUiState.Success -> {
                PokemonListContent(
                    lazyPagingItems = lazyPagingItems,
                    onItemClick = { pokemon -> onPokemonClick(pokemon.name) }
                )
            }
        }
    }
}
