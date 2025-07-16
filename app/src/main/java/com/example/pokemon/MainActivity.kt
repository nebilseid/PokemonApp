package com.example.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pokemon.ui.theme.PokemonTheme
import com.example.presentation.navigation.Routes
import com.example.presentation.ui.main.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTheme {
                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val destination = currentBackStackEntry?.destination
                val arguments = currentBackStackEntry?.arguments

                val (title, canGoBack) = when (destination?.route?.substringBefore("/{")) {
                    Routes.POKEMON_DETAIL -> {
                        val pokemonName = arguments?.getString("pokemonName") ?: "Detail"
                        // Capitalize first letter of pokemonName for title
                        pokemonName.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } to true
                    }
                    Routes.POKEMON_LIST -> "Pokémons" to false
                    else -> "Pokémons" to false
                }

                MainScreen(
                    navController = navController,
                    title = title,
                    canNavigateBack = canGoBack
                )
            }
        }
    }
}
