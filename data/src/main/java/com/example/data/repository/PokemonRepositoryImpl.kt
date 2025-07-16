package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.api.PokemonApiService
import com.example.data.mapper.toPokemon
import com.example.data.mapper.toPokemonDetail
import com.example.data.paging.PokemonPagingSource
import com.example.domain.model.Pokemon
import com.example.domain.model.PokemonDetail
import com.example.domain.repository.PokemonRepository
import com.example.domain.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class PokemonRepositoryImpl(
    private val apiService: PokemonApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PokemonRepository {

    override fun getPokemonList(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { PokemonPagingSource(apiService) }
        ).flow
            .flowOn(dispatcher)
    }

    override suspend fun getPokemonDetail(nameOrId: String): Result<PokemonDetail> = withContext(dispatcher) {
        try {
            val response = apiService.getPokemonDetail(nameOrId)
            Result.Success(response.toPokemonDetail())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}


