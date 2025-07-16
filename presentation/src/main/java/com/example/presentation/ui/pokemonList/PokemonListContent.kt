package com.example.presentation.ui.pokemonList

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
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
    onItemClick: (Pokemon) -> Unit,
    listState: LazyListState
) {
    val loadState = lazyPagingItems.loadState

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState
    ) {
        // If empty after refresh
        if (loadState.refresh is LoadState.NotLoading && lazyPagingItems.itemCount == 0) {
            item { EmptyScreen() }
        }

        items(
            count = lazyPagingItems.itemCount,
            key = { index -> lazyPagingItems.peek(index)?.name ?: index }
        ) { index ->
            lazyPagingItems[index]?.let { pokemon ->
                PokemonListItem(pokemon = pokemon, onClick = { onItemClick(pokemon) })
            }
        }

        when {
            loadState.append is LoadState.Loading -> item {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }

            loadState.append is LoadState.Error -> {
                val error = loadState.append as LoadState.Error
                item {
                    ErrorScreen(
                        message = error.error.localizedMessage ?: "Failed to load more",
                        onRetry = { lazyPagingItems.retry() }
                    )
                }
            }

            loadState.refresh is LoadState.Loading -> item {
                LoadingScreen()
            }

            loadState.refresh is LoadState.Error -> {
                val error = loadState.refresh as LoadState.Error
                item {
                    ErrorScreen(
                        message = error.error.localizedMessage ?: "Failed to load data",
                        onRetry = { lazyPagingItems.retry() }
                    )
                }
            }
        }
    }
}
