package com.example.pokedex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.model.PokeApiResponse
import com.example.pokedex.network.PokemonRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val pokemonRepo: PokemonRepositoryImp) :
    ViewModel() {

    private val _pokemon = MutableLiveData<List<PokeApiResponse>>()
    val pokemon: LiveData<List<PokeApiResponse>>
        get() = _pokemon

    var pokemonSelected: PokeApiResponse? = null

    init {
        fetchPokemon()
    }

    private fun fetchPokemon() {
        viewModelScope.launch {
            pokemonRepo.fetchPokemon("bulbasaur").let {
//                _pokemon.postValue(it)
            }
        }
    }
}