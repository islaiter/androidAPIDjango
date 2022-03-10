package com.example.pelis_api_kotlin

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.pelis_api_kotlin.objects.Usuario
import com.example.pelis_api_kotlin.activityPelicula
import com.example.pelis_api_kotlin.activitySocio


class activityMenu : AppCompatActivity() {

    private lateinit var btnActivityPeliculas: Button
    private lateinit var btnActivitySocios: Button
    private lateinit var txtBienvenidaUsername: TextView
    private lateinit var user: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = getIntent().getSerializableExtra("usuario") as Usuario
        setContentView(R.layout.menu_opciones_activity)

        InitView()
        GuardarBoton()
        IniciarListenerPeliculasYSocios()
    }

    private fun IniciarListenerPeliculasYSocios(){
        btnActivityPeliculas.setOnClickListener {
            PeliculasActivity(user)
        }

        btnActivitySocios.setOnClickListener {
            SociosActivity(user)
        }
    }

    private fun InitView(){
        btnActivityPeliculas = findViewById(R.id.btnPeliculasActivity)
        btnActivitySocios = findViewById(R.id.btnSociosActivity)
        txtBienvenidaUsername = findViewById(R.id.txtNombreUsuario)

    }
    private fun PeliculasActivity( user: Usuario) {
        val intent= Intent(this, activityPelicula::class.java)
        intent.putExtra("usuario", user)

        startActivity(intent)

    }

    private fun SociosActivity( user: Usuario) {
        val intent= Intent(this, activitySocio::class.java)
        intent.putExtra("usuario", user)

        startActivity(intent)
    }
    private fun GuardarBoton() {
        txtBienvenidaUsername.text = "Bienvenid@ ${user.username}"
        if(user.isStaff == false){
            btnActivitySocios.setVisibility(View.INVISIBLE)

        }
    }
}






