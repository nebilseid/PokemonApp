package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.model.Pokemon
import com.example.domain.repository.PokemonRepository
import com.example.domain.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(): Flow<PagingData<Pokemon>> {
        return repository.getPokemonList()
    }
}

