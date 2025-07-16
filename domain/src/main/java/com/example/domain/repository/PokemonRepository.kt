package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.Pokemon
import com.example.domain.model.PokemonDetail
import com.example.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<Pokemon>>
    suspend fun getPokemonDetail(nameOrId: String): Result<PokemonDetail>
}
