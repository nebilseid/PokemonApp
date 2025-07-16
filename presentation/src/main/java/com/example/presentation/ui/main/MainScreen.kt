package com.example.presentation.ui.main

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.presentation.navigation.AppNavGraph
import com.example.presentation.ui.components.AppToolbar

@Composable
fun MainScreen(
    navController: NavHostController,
    title: String,
    canNavigateBack: Boolean,
    listState: LazyListState
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
            contentPadding = innerPadding,
            listState = listState
        )
    }
}

