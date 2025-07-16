package com.example.domain.usecase

import com.example.domain.model.Pokemon
import com.example.domain.repository.PokemonRepository
import com.example.domain.util.Result

class GetPokemonListUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(): Result<List<Pokemon>> {
        return try {
            Result.Success(repository.getPokemonList())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}