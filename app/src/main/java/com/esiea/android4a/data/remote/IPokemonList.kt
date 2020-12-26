package com.esiea.android4a.data.remote

import com.esiea.android4a.data.local.models.Pokedex
import retrofit2.http.GET

interface IPokemonList {
    @get:GET("pokedex.json")
    val listPokemon:io.reactivex.rxjava3.core.Observable<Pokedex>

}