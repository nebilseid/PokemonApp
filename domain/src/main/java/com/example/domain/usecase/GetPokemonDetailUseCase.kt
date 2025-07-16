package com.example.domain.usecase

import com.example.domain.model.PokemonDetail
import com.example.domain.repository.PokemonRepository
import com.example.domain.util.Result
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(nameOrId: String): Result<PokemonDetail> {
        return repository.getPokemonDetail(nameOrId)
    }
}
