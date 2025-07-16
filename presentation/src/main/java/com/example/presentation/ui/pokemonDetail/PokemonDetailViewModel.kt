package com.example.presentation.ui.pokemonDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetPokemonDetailUseCase
import com.example.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PokemonDetailUiState>(PokemonDetailUiState.Loading)
    val uiState: StateFlow<PokemonDetailUiState> = _uiState.asStateFlow()

    fun loadPokemonDetail(nameOrId: String) {
        viewModelScope.launch {
            _uiState.value = PokemonDetailUiState.Loading

            when (val result = getPokemonDetailUseCase(nameOrId)) {
                is Result.Success -> _uiState.value = PokemonDetailUiState.Success(result.data)
                is Result.Error -> _uiState.value = PokemonDetailUiState.Error(
                    result.exception.message ?: "Something went wrong"
                )
                else -> Unit
            }
        }
    }

}
