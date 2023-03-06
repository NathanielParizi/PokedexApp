package com.example.pokedex.ui

import android.util.Log
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


    private val firstName = MutableLiveData<String>()
    private val lastName = MutableLiveData<String>()
    private val accountNumber = MutableLiveData<Int>()
    private val balance = MutableLiveData<Int>()
    private val baseExp = MutableLiveData<Int>()

    private val _pokemon = MutableLiveData<PokeApiResponse>()
    val pokemon: LiveData<PokeApiResponse>
        get() =_pokemon

    var pokemonSelected: PokeApiResponse? = null

    init {
        fetchPokemon()
    }

    fun isEnoughMoney(): Boolean {
       return (balance.value!! >= baseExp.value?.times(6)!!)
    }

    private fun fetchPokemon() {
        viewModelScope.launch {
            pokemonRepo.fetchPokemon("bulbasaur").let {
                Log.d("GOLDTAG", "${it}")
                _pokemon.postValue(it)
                baseExp.postValue(it.baseExperience)
            }
        }
    }
}