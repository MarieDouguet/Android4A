package com.esiea.android4a

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esiea.android4a.data.remote.IPokemonList
import com.esiea.android4a.data.remote.RetrofitClient
import com.esiea.android4a.presentation.main.Common
import com.esiea.android4a.presentation.main.ItemOffsetDecoration
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable


class PokemonList : Fragment() {

    private var compositeDisposable = CompositeDisposable()
    private var iPokemonList: IPokemonList

    private lateinit var recycler_view: RecyclerView

    init {
        val retrofit = RetrofitClient.instance
        iPokemonList = retrofit.create(IPokemonList::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val itemView = inflater.inflate(R.layout.fragment_pokemon_list, container, true)
        recycler_view = itemView.findViewById(R.id.pokemon_recyclerview) as RecyclerView
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = GridLayoutManager(activity, 2)
        val itemDecoration = ItemOffsetDecoration(requireActivity(), R.dimen.spacing)
        recycler_view.addItemDecoration(itemDecoration)

        fetchData()


        return itemView
    }

    private fun fetchData() {

        compositeDisposable.add(iPokemonList.listPokemon
            .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { pokemonDex ->
                Common.pokemonList = pokemonDex.pokemon!!
                val adapter = PokemonListAdapter(requireActivity(), Common.pokemonList)
                recycler_view.adapter = adapter
            })
    }


}