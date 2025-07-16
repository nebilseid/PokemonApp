package com.example.pokemon

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.presentation.ui.pokemonList.PokemonListScreen
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var fakeViewModel: FakePokemonListViewModel

    @Before
    fun setUp() {
        fakeViewModel = FakePokemonListViewModel()
    }

    @Test
    fun pokemonList_displaysPokemonNames() {
        composeTestRule.setContent {
            PokemonListScreen(
                viewModel = fakeViewModel,
                onPokemonClick = {},
                contentPadding = PaddingValues(0.dp)
            )
        }

        composeTestRule.onNodeWithText("Bulbasaur", substring = true)
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Charmander", substring = true)
            .assertIsDisplayed()
    }

    @Test
    fun clickOnPokemon_triggersCallback() {
        var clickedPokemon: String? = null

        composeTestRule.setContent {
            PokemonListScreen(
                viewModel = fakeViewModel,
                onPokemonClick = { clickedPokemon = it },
                contentPadding = PaddingValues(0.dp)
            )
        }

        composeTestRule.onNodeWithText("Charmander", substring = true)
            .performClick()

        composeTestRule.runOnIdle {
            assertEquals("Charmander", clickedPokemon)
        }
    }
}
