package com.example.data.api

import com.example.data.model.PokemonDetailDto
import com.example.data.model.PokemonListResponseDto
import com.example.data.network.Endpoints
import com.example.data.network.QueryParams
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokemonApiService {

    @GET(Endpoints.POKEMON_LIST)
    suspend fun getPokemonList(
        @Query(QueryParams.LIMIT) limit: Int,
        @Query(QueryParams.OFFSET) offset: Int
    ): PokemonListResponseDto

    @GET(Endpoints.POKEMON_DETAIL)
    suspend fun getPokemonDetail(
        @Path("idOrName") idOrName: String
    ): PokemonDetailDto
}