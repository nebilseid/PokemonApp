package com.example.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonListResponseDto(
    val results: List<PokemonDto>
)

@JsonClass(generateAdapter = true)
data class PokemonDto(
    val name: String,
    val url: String
)

@JsonClass(generateAdapter = true)
data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: SpritesDto,
    val types: List<TypeSlotDto>,
    val abilities: List<AbilitySlotDto>
)

@JsonClass(generateAdapter = true)
data class SpritesDto(
    val front_default: String?
)

@JsonClass(generateAdapter = true)
data class TypeSlotDto(
    val slot: Int,
    val type: TypeDto
)

@JsonClass(generateAdapter = true)
data class TypeDto(
    val name: String,
    val url: String
)

@JsonClass(generateAdapter = true)
data class AbilitySlotDto(
    val ability: AbilityDto
)

@JsonClass(generateAdapter = true)
data class AbilityDto(
    val name: String,
    val url: String
)
