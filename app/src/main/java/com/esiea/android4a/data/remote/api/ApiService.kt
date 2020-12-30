package com.esiea.android4a.data.remote.api

import com.esiea.android4a.data.local.models.Result
import retrofit2.Call
import retrofit2.http.GET

interface ApiService{
    @GET("pokedex.json")

    fun fetchAllPokemon(): Call<Result>

}