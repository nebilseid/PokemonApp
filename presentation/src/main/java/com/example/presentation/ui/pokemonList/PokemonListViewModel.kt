package com.example.presentation.ui.pokemonList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.Pokemon
import com.example.domain.usecase.GetPokemonListUseCase
import com.example.presentation.ui.state.PokemonListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel(), PokemonListViewModelType {

    private val _uiState = MutableStateFlow<PokemonListUiState>(PokemonListUiState.Loading)
    override val uiState: StateFlow<PokemonListUiState> = _uiState.asStateFlow()

    // Public PagingData exposed cleanly
    private val _pagingFlow = getPokemonListUseCase().cachedIn(viewModelScope)
    override val pokemonPagingFlow: Flow<PagingData<Pokemon>> = _pagingFlow

    private val _navigateToDetail = MutableSharedFlow<String>()
    override val navigateToDetail: SharedFlow<String> = _navigateToDetail.asSharedFlow()

    override fun onPokemonClick(pokemon: Pokemon) {
        viewModelScope.launch {
            _navigateToDetail.emit(pokemon.name)
        }
    }

    init {
        // One-time collection for UI state
        viewModelScope.launch {
            _pagingFlow
                .onStart { _uiState.value = PokemonListUiState.Loading }
                .catch { throwable ->
                    _uiState.value = PokemonListUiState.Error(
                        throwable.localizedMessage ?: "Unknown Error"
                    )
                }
                .collect { it ->
                    _uiState.value = if (it.toString().isEmpty()) {
                        PokemonListUiState.Empty
                    } else {
                        PokemonListUiState.Success(it)
                    }
                }
        }
    }
}
