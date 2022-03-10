package com.example.pelis_api_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pelis_api_kotlin.API
import com.example.pelis_api_kotlin.R
import com.example.pelis_api_kotlin.adapter.SocioAdapter
import com.example.pelis_api_kotlin.objects.Socio
import com.example.pelis_api_kotlin.objects.Usuario



import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class activitySocio: AppCompatActivity() {

    private lateinit var  user: Usuario
    var allsocios = mutableListOf<Socio>()


    override fun onCreate(savedInstanceState: Bundle?) {
        user= getIntent().getSerializableExtra("usuario") as Usuario
        super.onCreate(savedInstanceState)
        getsocios(user)
        setContentView(R.layout.muestra_socios_activity)



        initRecyclerView2()
        var btn:Button = findViewById(R.id.btnRefrescarSocios)
        var btnNuevo:Button = findViewById(R.id.btnNuevoSoc)

        btn.setOnClickListener {
            getsocios(user)
            initRecyclerView2()
            //var recyclerView = findViewById<RecyclerView>(R.id.recyclerSocios)
            //recyclerView.layoutManager = LinearLayoutManager(this)
            //recyclerView.adapter = SocioAdapter(allsocios)
        }

        btnNuevo.setOnClickListener {
            val intent = Intent(this, activityCustomSocio::class.java)
            var socio_vacio = Socio(0,"","","")
            intent.putExtra("control",0)
            intent.putExtra("usuario",user)
            intent.putExtra("socio",socio_vacio)
            startActivity(intent)
        }

    }

    private fun initRecyclerView2() {
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerSocios)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SocioAdapter(allsocios,this,user)

    }

    private fun getsocios(u: Usuario?) {
        CoroutineScope(Dispatchers.IO).launch {

                val tokensito:String = u!!.token
                val socios: Response<List<Socio>> = API.retrofitService.getsocios(tokensito)
                if (socios.isSuccessful){
                    allsocios = (socios.body()?: emptyList()) as MutableList<Socio>
                    showSuccesful()
                    println(allsocios)

                    println("funciona socios")
                }else{
                    showError()
                }


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