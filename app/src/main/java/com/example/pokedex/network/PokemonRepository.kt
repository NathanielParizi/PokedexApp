package com.example.pokedex.network

import com.example.pokedex.model.PokeApiResponse
import javax.inject.Inject

interface PokemonRepository{
    suspend fun fetchPokemon():List<PokeApiResponse>
}

class PokemonRepositoryImp @Inject constructor(retroAPIService: RetroApiService): PokemonRepository {
    private val retroAPI: RetroAPI = RetroAPI(retroAPIService)

    override suspend fun fetchPokemon(): List<PokeApiResponse> {
        return retroAPI.fetchPokemon()
    }

}
