package com.example.domain.model

data class Pokemon(
    val name: String,
    val id: Int
)

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val imageUrl: String?,
    val types: List<String> // List of type names
)

