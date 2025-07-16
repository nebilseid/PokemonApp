package com.example.data.api

import com.example.data.model.PokemonDetailDto
import com.example.data.model.PokemonListResponseDto
import com.example.data.network.EndPoints
import com.example.data.network.QueryParams
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    @GET(EndPoints.POKEMON_LIST)
    suspend fun getPokemonList(
        @Query(QueryParams.LIMIT) limit: Int,
        @Query(QueryParams.OFFSET) offset: Int
    ): PokemonListResponseDto

    @GET(EndPoints.POKEMON_DETAIL)
    suspend fun getPokemonDetail(
        @Path("idOrName") idOrName: String
    ): PokemonDetailDto
}
