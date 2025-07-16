package com.example.data.mapper

import com.example.data.model.PokemonDetailDto
import com.example.data.model.PokemonDto
import com.example.data.util.extractIdFromUrl
import com.example.domain.model.*

fun PokemonDto.toPokemon(): Pokemon {
    return Pokemon(
        name = name,
        id = extractIdFromUrl(url)
    )
}

// Mapper for Detail
fun PokemonDetailDto.toPokemonDetail(): PokemonDetail {
    return PokemonDetail(
        id = id,
        name = name,
        height = height,
        weight = weight,
        imageUrl = sprites.front_default,
        types = types.sortedBy { it.slot }.map { it.type.name }
    )
}

