package com.esiea.android4a.presentation.main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.esiea.android4a.R
import com.esiea.android4a.data.local.models.Pokemon
import com.esiea.android4a.data.remote.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.content_pokemon_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import com.esiea.android4a.data.local.models.Result
import kotlinx.android.synthetic.main.activity_detail_pokemon.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.pokemon_row.*


class PokemonListActivity : AppCompatActivity(), PokemonAdapter.OnPokemonItemClickListener {
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_pokemon_list)


        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle("Pokedex")

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/")
            .build()

        val api = retrofit.create(ApiService::class.java)
        api.fetchAllPokemon().enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.body()!!.pokemon.isNotEmpty()) {
                    showData(response.body()!!.pokemon)
                }
                Toast.makeText(this@PokemonListActivity, "response successful", Toast.LENGTH_LONG)
                //d("pokemon", "onResponse : ${response.body()!![0].name} ")
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                if (t is IOException) {
                    Toast.makeText(this@PokemonListActivity, "network error", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(this@PokemonListActivity, "conversion error", Toast.LENGTH_LONG)
                        .show()
                    Log.e("CONVERSION ERROR : ", t.message)
                }
                //d("pokemon", "onFailure")
            }
        })
    }


    private fun showData(pokemonList: MutableList<Pokemon>) {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@PokemonListActivity)
        recyclerView.adapter = PokemonAdapter(pokemonList, this)
    }


    override fun onItemClick(pokemon: Pokemon, position: Int) {

        val intent = Intent(this, DetailPokemonActivity::class.java)

        intent.putExtra("POKEMON_NAME", pokemon.name)
        intent.putExtra("POKEMON_TYPE", pokemon.type.toString())
        intent.putExtra("POKEMON_NUM", pokemon.num)
        intent.putExtra("POKEMON_HEIGHT", pokemon.height)
        intent.putExtra("POKEMON_WEIGHT", pokemon.weight)
        intent.putExtra("POKEMON_WEAKNESSES", pokemon.weaknesses.toString())

        startActivity(intent)


    }
}