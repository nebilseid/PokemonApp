package com.example.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.*
import com.example.presentation.ui.pokemonDetail.PokemonDetailScreen
import com.example.presentation.ui.pokemonList.PokemonListScreen
import com.example.presentation.ui.pokemonList.PokemonListViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.POKEMON_LIST,
        modifier = modifier
    ) {
        composable(Routes.POKEMON_LIST) {
            val viewModel: PokemonListViewModel = hiltViewModel()
            PokemonListScreen(
                viewModel = viewModel,
                onPokemonClick = { pokemonName ->
                    navController.navigate("${Routes.POKEMON_DETAIL}/$pokemonName")
                },
                contentPadding = contentPadding
            )
        }

        composable("${Routes.POKEMON_DETAIL}/{pokemonName}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("pokemonName") ?: return@composable
            PokemonDetailScreen(
                nameOrId = name,
                canNavigateBack = true,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
