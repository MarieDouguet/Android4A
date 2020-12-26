package com.esiea.android4a.data.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var ourInstance:Retrofit?=null

    val instance:Retrofit
    get(){
        if (ourInstance == null)
            ourInstance = Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        return ourInstance!!
    }
}