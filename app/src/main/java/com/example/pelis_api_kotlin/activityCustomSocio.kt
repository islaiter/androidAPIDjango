package com.example.pelis_api_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pelis_api_kotlin.API
import com.example.pelis_api_kotlin.R
import com.example.pelis_api_kotlin.objects.Socio
import com.example.pelis_api_kotlin.objects.Usuario

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class activityCustomSocio: AppCompatActivity() {

    private lateinit var  user: Usuario
    private lateinit var  soc: Socio
    private  var  control:Int = 0

    private lateinit var btnAñadir: Button
    private lateinit var btnPUT: Button
    private lateinit var btnDELETE: Button

    private  lateinit var editID: EditText
    private  lateinit var editNIF: EditText
    private  lateinit var editTFL: EditText
    private  lateinit var editUserId: EditText



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        user= getIntent().getSerializableExtra("usuario") as Usuario
        soc = getIntent().getSerializableExtra("socio") as Socio
        control = getIntent().getIntExtra("control",1)
        setContentView(R.layout.custom_soc_activity)

        btnAñadir = findViewById(R.id.btnCREATE)
        btnPUT = findViewById(R.id.btnPUT)
        btnDELETE = findViewById(R.id.btnDELETE)

        editID = findViewById(R.id.editID)
        editNIF = findViewById(R.id.editNIF)
        editTFL = findViewById(R.id.editTlf)
        editUserId = findViewById(R.id.editUserId)

        if (control == 1){
            //DESACTIVAR BOTONES
            editID.setText(soc.id.toString())
            editNIF.setText(soc.NIF_soc)
            editTFL.setText(soc.telefono_soc)
            editUserId.setText(soc.user)
            btnAñadir.isEnabled = false
        }else{
            //DESACTIVAR BOTONES
            editID.setText("")
            editID.isEnabled = false
            editNIF.setText("")
            editTFL.setText("")
            editUserId.setText("")
            btnDELETE.isEnabled = false
            btnPUT.isEnabled = false
        }

        InitListener()

    }

    private fun volverActivity(){
        val intent = Intent(this, activitySocio::class.java)
        intent.putExtra("usuario", user)
        startActivity(intent)
    }

    private fun InitListener() {
        btnAñadir.setOnClickListener {
            var id = editID.text.toString()
            var nif = editNIF.text.toString()
            var tfl = editTFL.text.toString()
            var userid = editUserId.text.toString()
            editID.text.clear()
            editNIF.text.clear()
            editTFL.text.clear()
            editUserId.text.clear()
            if (nif.isNotEmpty() && tfl.isNotEmpty()&& userid.isNotEmpty()){
                createSoc(user,id,nif,tfl,userid)
            }
        }

        btnPUT.setOnClickListener {
            var id = editID.text.toString()
            var nif = editNIF.text.toString()
            var tfl = editTFL.text.toString()
            var userid = editUserId.text.toString()
            editID.text.clear()
            editNIF.text.clear()
            editTFL.text.clear()
            editUserId.text.clear()
            if (id.isNotEmpty() && nif.isNotEmpty() && tfl.isNotEmpty()&& userid.isNotEmpty()){
                updateSoc(user,id,nif,tfl,userid)
            }
        }

        btnDELETE.setOnClickListener {
            var id = editID.text.toString()
            var nif = editNIF.text.toString()
            var tfl = editTFL.text.toString()
            var userid = editUserId.text.toString()
            editID.text.clear()
            editNIF.text.clear()
            editTFL.text.clear()
            editUserId.text.clear()
            if (id.isNotEmpty()){
                deleteSoc(user,id,nif,tfl,userid)
            }
        }
    }

    private fun createSoc(u: Usuario?,editId:String,editNIF:String,editTFL:String,editUserID:String) {
        CoroutineScope(Dispatchers.IO).launch {
            println(editId)
            var token = u!!.token
            var socios: Response<Socio> = API.retrofitService.createsocio(token,editNIF,editTFL,editUserID)
            if (socios.isSuccessful){
                val allsocios = socios.body()
                showSuccesful()
                println(allsocios)

                println("Creado")
                volverActivity()
            }else{
                showError()
            }


        }
    }

    private fun updateSoc(u: Usuario?,editId:String,editNIF:String,editTFL:String,editUserID:String) {
        CoroutineScope(Dispatchers.IO).launch {
            println(editId)
            var token = u!!.token
            var socios: Response<Socio> = API.retrofitService.putsocio(editId.toInt(),token,editNIF,editTFL,editUserID)
            if (socios.isSuccessful){
                val allsocios = socios.body()
                showSuccesful()
                println(allsocios)

                println("Actualizado")
                volverActivity()
            }else{
                showError()
            }
        }
    }

    private fun deleteSoc(u: Usuario?,editId:String,editNIF:String,editTFL:String,editUserID:String) {
        CoroutineScope(Dispatchers.IO).launch {
            println(editId)
            var token = u!!.token
            var socios: Response<Socio> = API.retrofitService.deletesocio(token,editId.toInt())
            if (socios.isSuccessful){
                val allsocios = socios.body()
                showSuccesful()
                println(allsocios)
                println("Borrado")
                volverActivity()
            }else{
                showError()
            }


        }
    }

    private fun showSuccesful() {
        runOnUiThread{
            Toast.makeText(this, "Conectado con éxito", Toast.LENGTH_SHORT).show()
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


