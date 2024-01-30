package com.example.pokemonworld

import com.example.pokemonworld.data.PokemonDetail
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

val retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/pokemon/").addConverterFactory(
    GsonConverterFactory.create()).build()

interface ApiService {
    @GET("{pokemonName}")
    /*
    In your ApiService, modify the return type of the getPokemon function to be Call<PokemonDetail>
     */
    suspend fun getPokemon(@Path("pokemonName") pokemonName: String): PokemonDetail
//    fun getPokemon(@Path("pokemonName") pokemonName: String): Call<PokemonDetail>

}

val retrofitService = retrofit.create(ApiService::class.java)