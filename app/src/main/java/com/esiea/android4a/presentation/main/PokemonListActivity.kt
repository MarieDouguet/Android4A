package com.esiea.android4a.presentation.main

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.esiea.android4a.R
import com.esiea.android4a.data.local.models.Pokemon
import com.esiea.android4a.data.remote.ApiService
import kotlinx.android.synthetic.main.content_pokemon_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_pokemon_list)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)
        api.fetchAllPokemon().enqueue(object : Callback<List<Pokemon>> {
            override fun onResponse(call: Call<List<Pokemon>>, response: Response<List<Pokemon>>) {
                d("pokemon", "onResponse ")
            }

            override fun onFailure(call: Call<List<Pokemon>>, t: Throwable) {
                d("pokemon", "onFailure")
            }

        })

        val pokemonList = mutableListOf<Pokemon>()
        for (i in 0..100) {
            pokemonList.add(Pokemon(1, "1", "pikachu"))
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@PokemonListActivity)
            adapter = PokemonAdapter(pokemonList)
        }

    }
}