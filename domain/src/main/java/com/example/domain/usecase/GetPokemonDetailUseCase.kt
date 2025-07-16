package com.example.domain.usecase

import com.example.domain.model.Pokemon
import com.example.domain.repository.PokemonRepository
import com.example.domain.util.Result

class GetPokemonDetailUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(nameOrId: String): Result<Pokemon> {
        return try {
            Result.Success(repository.getPokemonDetail(nameOrId))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}