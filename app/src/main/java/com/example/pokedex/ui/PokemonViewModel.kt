package com.example.pokedex.ui

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.R
import com.example.pokedex.model.PokeApiResponse
import com.example.pokedex.model.User
import com.example.pokedex.network.PokemonRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val pokemonRepo: PokemonRepositoryImp) :
    ViewModel() {

    private val _mUser = MutableLiveData<User>()
    val mUser: MutableLiveData<User> get() = _mUser

    private val mBaseExp = MutableLiveData<Int>()

    private val _pokemon = MutableLiveData<PokeApiResponse>()
    val pokemon: LiveData<PokeApiResponse>
        get() = _pokemon

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        fetchPokemon("bulbasaur")
        initializeUser()
    }

    fun updateUser(user: User) {
        _mUser.postValue(user)
    }

    private fun initializeUser() {
        val user = User("Ash", "Katchem", 1000, 123, "ashKetchem@gmail.com")
        _mUser.postValue(user)
    }

    fun isEnoughMoney(): Boolean {
        if (_mUser.value?.balance != null && mBaseExp.value != null) {
            return (_mUser.value?.balance!! >= mBaseExp.value?.times(6)!!)
        }
        return false
    }

    fun playSound(context: Context?) {
        val mediaPlayer = MediaPlayer.create(context, R.raw.purchase)
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer.start()
    }

    fun fetchPokemon(pokemonName: String) {
        viewModelScope.launch {
            try {
                val response = pokemonRepo.fetchPokemon(pokemonName.toLowerCase()).let {
                    _pokemon.postValue(it)
                    mBaseExp.postValue(it.baseExperience)
                }
                Result.success(response)

            } catch (e: Exception) {
                _errorMessage.postValue("Error fetching Pokemon: ${e.message}")
                Result.failure(e)
            }

        }
    }
}