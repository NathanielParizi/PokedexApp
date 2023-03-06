package com.example.pokedex.network

import com.example.pokedex.model.PokeApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject


interface RetroApiService {

    @GET("api/v2/pokemon/{pokemonName}")
    suspend fun fetchPokemon(@Path("pokemonName") pokemonName : String): PokeApiResponse

}

class RetroAPI @Inject constructor(private val retroAPIService: RetroApiService) {
    suspend fun fetchPokemon(pokemonName: String): PokeApiResponse {
        return retroAPIService.fetchPokemon(pokemonName)
    }
}

