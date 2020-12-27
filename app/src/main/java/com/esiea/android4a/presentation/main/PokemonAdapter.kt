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
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_row, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList.elementAtOrNull(position)
        holder.pokemon_name.text = pokemon?.name
        holder.pokemon_type.text=pokemon?.num
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    class PokemonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val pokemon_name: TextView = itemView.findViewById(R.id.pokemon_name)
        val pokemon_type : TextView = itemView.findViewById(R.id.pokemon_type)
    }


}
