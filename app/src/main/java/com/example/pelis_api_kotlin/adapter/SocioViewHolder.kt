package com.example.pelis_api_kotlin.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pelis_api_kotlin.R
import com.example.pelis_api_kotlin.objects.Socio
import com.example.pelis_api_kotlin.objects.Usuario
import com.example.pelis_api_kotlin.activityCustomSocio


class SocioViewHolder(var view: View, var contexto: Context , var usu: Usuario) : RecyclerView.ViewHolder(view) {
    val id = view.findViewById<TextView>(R.id.txtID)
    val NIF = view.findViewById<TextView>(R.id.txtNIFSoc)
    val telefono = view.findViewById<TextView>(R.id.txtTelefonoSoc)
    val user = view.findViewById<TextView>(R.id.txtUser)

    fun render(soc: Socio){
        id.text = soc.id.toString()
        NIF.text = soc.NIF_soc
        telefono.text = soc.telefono_soc
        user.text = soc.user

        view.setOnClickListener {
            val intent = Intent(contexto, activityCustomSocio::class.java)
            intent.putExtra("control",1)
            intent.putExtra("usuario",usu)
            intent.putExtra("socio",soc)
            contexto.startActivity(intent)
        }

    }
}