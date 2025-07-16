package com.example.domain.model

data class Pokemon(
    val name: String,
    val url: String
)

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val imageUrl: String?,
    val types: List<String>,
    val abilities: List<Ability>
)

data class Ability(
    val name: String
)
