package com.example.pokedex.network

import com.example.pokedex.model.PokeApiResponse
import retrofit2.http.GET
import javax.inject.Inject

interface RetroApiService {

    @GET("api/v2/pokemon/ditto")
    suspend fun fetchPokemon(): List<PokeApiResponse>
}

class RetroAPI @Inject constructor(private val retroAPIService: RetroApiService) {
    suspend fun fetchPokemon(): List<PokeApiResponse> {
        return retroAPIService.fetchPokemon()
    }
}

