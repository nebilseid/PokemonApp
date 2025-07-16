package com.example.presentation.ui.pokemonList

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.domain.model.Pokemon

@Composable
fun PokemonListContent(
    lazyPagingItems: LazyPagingItems<Pokemon>,
    onItemClick: (Pokemon) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(
            count = lazyPagingItems.itemCount,
            key = { index -> lazyPagingItems[index]?.name ?: index }
        ) { index ->
            lazyPagingItems[index]?.let { pokemon ->
                PokemonListItem(pokemon = pokemon, onClick = { onItemClick(pokemon) })
            }
        }

        lazyPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> item {
                    LoadingScreen()
                }

                loadState.append is LoadState.Loading -> item {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }

                loadState.refresh is LoadState.Error -> {
                    val e = loadState.refresh as LoadState.Error
                    item {
                        ErrorScreen(
                            message = e.error.localizedMessage ?: "Failed to load data",
                            onRetry = { retry() }
                        )
                    }
                }

                loadState.append is LoadState.Error -> {
                    val e = loadState.append as LoadState.Error
                    item {
                        ErrorScreen(
                            message = e.error.localizedMessage ?: "Failed to load more",
                            onRetry = { retry() }
                        )
                    }
                }

                loadState.refresh is LoadState.NotLoading && lazyPagingItems.itemCount == 0 -> {
                    item { EmptyScreen() }
                }
            }
        }
    }
}
