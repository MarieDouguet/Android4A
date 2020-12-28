package com.esiea.android4a.presentation.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import kotlinx.android.synthetic.main.activity_main.*


class PokemonListActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var editTextSearch: EditText
    lateinit var pokemonList: MutableList<Pokemon>
    lateinit var mAdapter: PokemonAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_pokemon_list)

        editTextSearch = findViewById(R.id.et_search)

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                filterList(s.toString())
            }

        })
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

    private fun filterList(filterItem: String) {

        var tempList: ArrayList<Pokemon> = ArrayList()
        var List: MutableList<Pokemon> = pokemonList

        for (d in List) {
            if (filterItem in d.name) {
                tempList.add(d)

            }
        }
        mAdapter.updateList(tempList)
    }

    private fun showData(pokemonList: MutableList<Pokemon>) {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@PokemonListActivity)
        recyclerView.adapter = PokemonAdapter(pokemonList)
    }
}