package com.example.pelis_api_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pelis_api_kotlin.R
import com.example.pelis_api_kotlin.objects.Pelicula

class PeliculaAdapter(private val peliculaList: List<Pelicula>): RecyclerView.Adapter<PeliculaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PeliculaViewHolder(layoutInflater.inflate(R.layout.item_peliculas,parent,false))

    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val item = peliculaList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = peliculaList.size



}