package com.example.presentation.ui.pokemonList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.presentation.ui.state.PokemonListUiState
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModelType,
    onPokemonClick: (String) -> Unit,
    contentPadding: PaddingValues,
    listState: LazyListState
) {
    val uiState by viewModel.uiState.collectAsState()

    val lazyPagingItems = viewModel.pokemonPagingFlow.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        when (uiState) {
            is PokemonListUiState.Loading -> LoadingScreen()
            is PokemonListUiState.Error -> ErrorScreen(
                message = (uiState as PokemonListUiState.Error).message,
                onRetry = { lazyPagingItems.retry() }
            )
            is PokemonListUiState.Empty -> EmptyScreen()
            is PokemonListUiState.Success -> {
                PokemonListContent(
                    listState = listState,
                    lazyPagingItems = lazyPagingItems,
                    onItemClick = { pokemon -> onPokemonClick(pokemon.name) }
                )
            }
        }
    }
}

