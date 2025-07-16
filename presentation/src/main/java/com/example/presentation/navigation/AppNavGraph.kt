package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
/*import com.example.presentation.ui.detail.PokemonDetailScreen
import com.example.presentation.ui.list.PokemonListScreen
import com.example.presentation.ui.list.PokemonListViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
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
                onItemClick = { pokemonName ->
                    navController.navigate("${Routes.POKEMON_DETAIL}/$pokemonName")
                }
            )
        }

        composable("${Routes.POKEMON_DETAIL}/{pokemonName}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("pokemonName") ?: return@composable
            PokemonDetailScreen(pokemonName = name)
        }
    }
}*/
