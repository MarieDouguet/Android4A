package com.esiea.android4a.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esiea.android4a.R
import com.esiea.android4a.data.local.models.Pokemon
import kotlinx.android.synthetic.main.pokemon_row.view.*

class PokemonAdapter(private val pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.pokemon_name.text = pokemon.name
        holder.pokemon_type.text=pokemon.num
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val pokemon_name: TextView = itemView.pokemon_name
        val pokemon_type : TextView = itemView.pokemon_type
    }


}
