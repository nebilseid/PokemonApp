package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.model.Pokemon
import com.example.domain.repository.PokemonRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetPokemonListUseCaseTest {

    private val repository: PokemonRepository = mockk()
    private lateinit var useCase: GetPokemonListUseCase

    @Before
    fun setup() {
        useCase = GetPokemonListUseCase(repository)
    }

    @Test
    fun `invoke returns PagingData flow`() = runTest {
        // Arrange
        val pokemonList = listOf(Pokemon("pikachu", "bulbasaur"))
        val pagingData = PagingData.from(pokemonList)

        coEvery { repository.getPokemonList() } returns flowOf(pagingData)

        // Act
        val result = useCase().first()

        // Assert
        assertNotNull(result)
    }
}
