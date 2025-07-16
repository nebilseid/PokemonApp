package com.example.presentation.ui.main

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.presentation.navigation.AppNavGraph
import com.example.presentation.ui.components.AppToolbar
@Composable
fun MainScreen(
    navController: NavHostController,
    title: String,
    canNavigateBack: Boolean
) {
    Scaffold(
        topBar = {
            AppToolbar(
                title = title,
                canNavigateBack = canNavigateBack,
                onNavigateBack = {
                    if (canNavigateBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            contentPadding = innerPadding
        )
    }
}
