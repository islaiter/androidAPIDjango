package com.example.pelis_api_kotlin

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pelis_api_kotlin.API
import com.example.pelis_api_kotlin.adapter.PeliculaAdapter
import com.example.pelis_api_kotlin.R
import com.example.pelis_api_kotlin.objects.Pelicula
import com.example.pelis_api_kotlin.objects.Usuario

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class activityPelicula: AppCompatActivity() {

    private lateinit var adapter: PeliculaAdapter

    var allpeliculas = mutableListOf<Pelicula>()
    private lateinit var  user: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        user= getIntent().getSerializableExtra("usuario") as Usuario
        super.onCreate(savedInstanceState)
        getpeliculas()
        setContentView(R.layout.muestra_peliculas_activity)
        initRecyclerView()
        var btnRefresh = findViewById<Button>(R.id.btnRefrescarPeliculas)


        btnRefresh.setOnClickListener {
            initRecyclerView()
        }
    }


    private fun initRecyclerView() {
        getpeliculas()
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerPeliculas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PeliculaAdapter(allpeliculas)

    }

    private fun getpeliculas() {
        //HACEMOS CORRUTINA
        CoroutineScope(Dispatchers.IO).launch {
            val peliculas: Response<List<Pelicula>> = API.retrofitService.getpeliculas()
            if (peliculas.isSuccessful){
                allpeliculas = (peliculas.body()?: emptyList()) as MutableList<Pelicula>
                showSuccesful()
            }else
                showError()
        }
    }

    private fun showSuccesful() {
        runOnUiThread{
            Toast.makeText(this, "Acceso Correcto", Toast.LENGTH_SHORT).show()
        }
    }

    //MOSTRAR TOAST AVISANDO DE ERROR
    private fun showError() {
        //Que lo haga en el hilo principal
        runOnUiThread{
            Toast.makeText(this, "ERROR LLAMADA", Toast.LENGTH_SHORT).show()
        }
    }
}