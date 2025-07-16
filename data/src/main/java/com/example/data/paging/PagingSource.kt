package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.api.PokemonApiService
import com.example.data.mapper.toPokemon
import com.example.domain.model.Pokemon

class PokemonPagingSource(
    private val apiService: PokemonApiService
) : PagingSource<Int, Pokemon>() {

    companion object {
        private const val STARTING_OFFSET = 0
        private const val PAGE_SIZE = 20
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val offset = params.key ?: STARTING_OFFSET
        return try {
            val response = apiService.getPokemonList(limit = params.loadSize, offset = offset)
            val pokemons = response.results.map { it.toPokemon() }
            val nextKey = if (pokemons.isEmpty()) null else offset + params.loadSize
            LoadResult.Page(
                data = pokemons,
                prevKey = if (offset == STARTING_OFFSET) null else offset - params.loadSize,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(PAGE_SIZE)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(PAGE_SIZE)
        }
    }
}
