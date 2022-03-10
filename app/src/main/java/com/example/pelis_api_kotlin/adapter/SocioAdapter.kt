package com.example.pelis_api_kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pelis_api_kotlin.R
import com.example.pelis_api_kotlin.objects.Socio
import com.example.pelis_api_kotlin.objects.Usuario


class SocioAdapter(private val socioList:List<Socio>, var contexto:Context, var usu: Usuario): RecyclerView.Adapter<SocioViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SocioViewHolder(layoutInflater.inflate(R.layout.item_socios,parent,false),contexto, usu)
    }

    override fun onBindViewHolder(holder: SocioViewHolder, position: Int) {
        val item = socioList[position]
        holder.render(item)


    }

    override fun getItemCount(): Int = socioList.size
}