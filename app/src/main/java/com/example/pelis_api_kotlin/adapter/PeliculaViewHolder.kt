package com.example.pelis_api_kotlin.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pelis_api_kotlin.R
import com.example.pelis_api_kotlin.objects.Pelicula

class PeliculaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tituloPeli = view.findViewById<TextView>(R.id.tvPeliculaTitutlo)
    val descriptionPeli = view.findViewById<TextView>(R.id.tvDescription)
    val portada = view.findViewById<ImageView>(R.id.ivPortada)

    fun render(peli : Pelicula){
        tituloPeli.text = peli.titulo
        descriptionPeli.text = peli.descripcion

        Glide.with(portada.context).load(peli.portada).into(portada)

    }
}