package com.esiea.android4a.presentation.main

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.esiea.android4a.R
import com.esiea.android4a.data.local.models.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_detail_pokemon.view.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailPokemonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pokemon)

        val textView_name: TextView
        val textView_type: TextView
        val textView_height: TextView
        val textView_weight: TextView
        val textView_weaknesses: TextView


        textView_name = findViewById(R.id.name)
        textView_type = findViewById(R.id.type)
        textView_height = findViewById(R.id.height)
        textView_weight = findViewById(R.id.weight)
        textView_weaknesses = findViewById(R.id.weaknesses)


        val name = getIntent().getStringExtra("POKEMON_NAME")
        val type = getIntent().getStringExtra("POKEMON_TYPE")
        val num = getIntent().getStringExtra("POKEMON_NUM")
        val height = getIntent().getStringExtra("POKEMON_HEIGHT")
        val weight = getIntent().getStringExtra("POKEMON_WEIGHT")
        val weaknesses = getIntent().getStringExtra("POKEMON_WEAKNESSES")


        textView_name.setText(name)
        textView_type.setText(type)
        textView_height.setText(height)
        textView_weight.setText(weight)
        textView_weaknesses.setText(weaknesses)

        val image: ImageView = findViewById(R.id.image)
        val url = "http://www.serebii.net/pokemongo/pokemon/" + num + ".png"
        Picasso.get()
            .load(url)
            .into(image)
    }
}