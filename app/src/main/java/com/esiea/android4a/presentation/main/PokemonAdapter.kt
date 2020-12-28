package com.esiea.android4a.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.SimpleTarget
import com.esiea.android4a.R
import com.esiea.android4a.data.local.models.Pokemon
import kotlinx.android.synthetic.main.content_pokemon_list.view.*
import kotlinx.android.synthetic.main.pokemon_row.view.*
import java.util.*
import kotlin.collections.ArrayList

class PokemonAdapter(private val pokemonList: MutableList<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_row, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList.elementAtOrNull(position)
        holder.pokemon_name.text = pokemon?.name
        holder.pokemon_type.text = pokemon?.type.toString()

        Glide.with(holder.itemView.context)
            .load(pokemon?.img)
            .override(1000, 1000)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.pokemon_image)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pokemon_name: TextView = itemView.findViewById(R.id.pokemon_name)
        val pokemon_type: TextView = itemView.findViewById(R.id.pokemon_type)
        val pokemon_image: ImageView = itemView.findViewById(R.id.pokemon_image)
    }

    fun updateList(list : MutableList<Pokemon>){
        var pokemonlist = pokemonList
        pokemonlist = list
        notifyDataSetChanged()
    }
}
