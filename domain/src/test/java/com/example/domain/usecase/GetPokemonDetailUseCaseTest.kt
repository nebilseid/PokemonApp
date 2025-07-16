package com.example.domain.usecase

import com.example.domain.model.Ability
import com.example.domain.model.PokemonDetail
import com.example.domain.repository.PokemonRepository
import com.example.domain.util.Result
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetPokemonDetailUseCaseTest {

    private val repository = mockk<PokemonRepository>()
    private lateinit var useCase: GetPokemonDetailUseCase

    @Before
    fun setup() {
        useCase = GetPokemonDetailUseCase(repository)
    }

    @Test
    fun `invoke returns success result`() = runTest {
        val pokemonDetail = PokemonDetail(
            id = 1,
            name = "bulbasaur",
            height = 7,
            weight = 69,
            imageUrl = "https://pokeapi.co/media/sprites/pokemon/1.png",
            types = listOf("grass", "poison"),
            abilities = listOf(Ability("overgrow"))
        )

        coEvery { repository.getPokemonDetail("bulbasaur") } returns Result.Success(pokemonDetail)

        val result = useCase("bulbasaur")

        assertTrue(result is Result.Success)
        assertEquals(pokemonDetail, (result as Result.Success).data)
    }

    @Test
    fun `invoke returns error result`() = runTest {
        val exception = Exception("Not found")

        coEvery { repository.getPokemonDetail("missingno") } returns Result.Error(exception)

        val result = useCase("missingno")

        assertTrue(result is Result.Error)
        assertEquals(exception, (result as Result.Error).exception)
    }
}
