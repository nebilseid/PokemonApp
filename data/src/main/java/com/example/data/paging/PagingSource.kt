package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.api.PokemonApiService
import com.example.data.mapper.toPokemon
import com.example.domain.model.Pokemon
import retrofit2.HttpException
import java.io.IOException

class PokemonPagingSource(
    private val apiService: PokemonApiService
) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val offset = params.key ?: 0
        val limit = params.loadSize

        return try {
            val response = apiService.getPokemonList(limit = limit, offset = offset)
            val pokemons = response.results.map { it.toPokemon() }

            LoadResult.Page(
                data = pokemons,
                prevKey = if (offset == 0) null else offset - limit,
                nextKey = if (pokemons.isEmpty()) null else offset + limit
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.plus(PAGE_SIZE) ?: page?.nextKey?.minus(PAGE_SIZE)
        }
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}
