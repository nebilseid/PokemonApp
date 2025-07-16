package com.example.data.mapper

import com.example.data.model.PokemonDetailDto
import com.example.data.model.PokemonDto
import com.example.domain.model.Ability
import com.example.domain.model.Pokemon
import com.example.domain.model.PokemonDetail

fun PokemonDto.toPokemon(): Pokemon {
    return Pokemon(
        name = name,
        url = url
    )
}

fun PokemonDetailDto.toPokemonDetail(): PokemonDetail {
    return PokemonDetail(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        height = height,
        weight = weight,
        imageUrl = sprites.front_default.orEmpty(),
        types = types
            .sortedBy { it.slot }
            .map { it.type.name.replaceFirstChar { it.uppercase() } },
        abilities = abilities.map { Ability(it.ability.name.replaceFirstChar { it.uppercase() }) }
    )
}
