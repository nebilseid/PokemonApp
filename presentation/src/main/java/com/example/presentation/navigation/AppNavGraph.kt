package com.example.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.ui.pokemonDetail.PokemonDetailScreen
import com.example.presentation.ui.pokemonList.PokemonListScreen
import com.example.presentation.ui.pokemonList.PokemonListViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    contentPadding: PaddingValues,
    listState: LazyListState
) {
    NavHost(
        navController = navController,
        startDestination = Routes.POKEMON_LIST
    ) {
        composable(Routes.POKEMON_LIST) {
            val viewModel: PokemonListViewModel = hiltViewModel()
            PokemonListScreen(
                viewModel = viewModel,
                onPokemonClick = { name ->
                    navController.navigate("${Routes.POKEMON_DETAIL}/$name")
                },
                contentPadding = contentPadding,
                listState = listState
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
