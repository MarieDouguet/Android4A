package com.esiea.android4a.data.remote

import com.esiea.android4a.data.local.models.Pokemon
import retrofit2.Call
import retrofit2.http.GET

interface ApiService{
    @GET("pokedex.json")

    fun fetchAllPokemon(): Call<List<Pokemon>>

}