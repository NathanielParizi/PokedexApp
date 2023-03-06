package com.example.pokedex.network

import com.example.pokedex.model.PokeApiResponse
import retrofit2.Call
import javax.inject.Inject

interface PokemonRepository {
    suspend fun fetchPokemon(pokemonName: String): Call<PokeApiResponse>
}

class PokemonRepositoryImp @Inject constructor(retroAPIService: RetroApiService) :
    PokemonRepository {
    private val retroAPI: RetroAPI = RetroAPI(retroAPIService)

    override suspend fun fetchPokemon(pokemonName: String): Call<PokeApiResponse> {
        return retroAPI.fetchPokemon(pokemonName)
    }

}
