package com.example.presentation.ui.pokemonDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.domain.model.PokemonDetail
import com.example.presentation.ui.common.LabelRes
import com.example.presentation.ui.components.AppToolbar
import com.example.presentation.ui.components.InfoRow

@Composable
fun PokemonDetailScreen(
    nameOrId: String,
    viewModel: PokemonDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    canNavigateBack: Boolean
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(nameOrId) {
        viewModel.loadPokemonDetail(nameOrId)
    }

    Scaffold(
        topBar = {
            AppToolbar(
                title = nameOrId.replaceFirstChar { it.uppercase() },
                canNavigateBack = canNavigateBack,
                onNavigateBack = onBackClick
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (uiState) {
                is PokemonDetailUiState.Loading -> LoadingScreen()
                is PokemonDetailUiState.Error -> {
                    ErrorScreen(
                        message = (uiState as PokemonDetailUiState.Error).message,
                        onRetry = { viewModel.loadPokemonDetail(nameOrId) }
                    )
                }

                is PokemonDetailUiState.Success -> {
                    DetailContent((uiState as PokemonDetailUiState.Success).pokemonDetail)
                }
            }
        }
    }
}

@Composable
fun DetailContent(pokemon: PokemonDetail) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(pokemon.imageUrl),
            contentDescription = pokemon.name,
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        InfoRow(LabelRes.Name, pokemon.name)
        InfoRow(LabelRes.Height, pokemon.height)
        InfoRow(LabelRes.Weight, pokemon.weight)
        InfoRow(LabelRes.Types, pokemon.types.joinToString(", "))
        InfoRow(LabelRes.Abilities, pokemon.abilities.joinToString(", ") { it.name })
    }
}

@Composable
fun LoadingScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(message, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onRetry) {
                Text("Retry")
            }
        }
    }
}
