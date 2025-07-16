package com.example.presentation.ui.main

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presentation.navigation.Routes

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val listState = rememberSaveable(saver = LazyListState.Saver) { LazyListState() }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val destination = currentBackStackEntry?.destination
    val arguments = currentBackStackEntry?.arguments

    val (title, canGoBack) = when (destination?.route?.substringBefore("/{")) {
        Routes.POKEMON_DETAIL -> {
            val pokemonName = arguments?.getString("pokemonName") ?: "Detail"
            pokemonName.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } to true
        }
        Routes.POKEMON_LIST -> "Pokémons" to false
        else -> "Pokémons" to false
    }

    MainScreen(
        navController = navController,
        title = title,
        canNavigateBack = canGoBack,
        listState = listState
    )
}
