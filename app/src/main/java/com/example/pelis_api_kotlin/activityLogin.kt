package com.example.pelis_api_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.pelis_api_kotlin.API.retrofitService
import com.example.pelis_api_kotlin.objects.Socio
import com.example.pelis_api_kotlin.objects.Token
import com.example.pelis_api_kotlin.objects.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class activityLogin : AppCompatActivity() {
    private lateinit var btnLogin:Button
    private lateinit var etUsername:EditText
    private lateinit var etPassword:EditText
    private lateinit var txtGuia:TextView

    var allsocios = emptyList<Socio>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        InitView()
        //getpeliculas()
        InitListener()
    }

    private fun InitListener() {
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            etUsername.text.clear()
            etPassword.text.clear()
            if (username.isNotEmpty() && password.isNotEmpty()){
                getTextToken(username,password)

            }
        }
    }

    private fun menuActivity( user: Usuario){
        val intent = Intent(this, activityMenu::class.java)
        intent.putExtra("usuario",user)
        //intent.putExtra("username", user.username)
        //intent.putExtra("token", user.token)
        //intent.putExtra("isStaff", user.isStaff)
        startActivity(intent)
    }

    private fun getTextToken(username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result: Response<Token> = retrofitService.getToken(username,password)
            if (result.isSuccessful){
                //checkResult(result.body())
                val a:Token? = result.body()

                val t: String = ComprobarToken(a).toString()
                val staff:Boolean = ComprobarStaff(t)
                //println(staff)
                val user:Usuario = Usuario(username,t,staff)
                //getsocios(user)
                runOnUiThread{
                    println(user.isStaff)
                    menuActivity(user)
                }
            }else{
                showError()
            }
        }
    }
    private fun ComprobarToken(t: Token?): String? {
        var envio:String? = null
        if (t != null && !t.token.isNullOrEmpty()){
            val tokensito:String = t.token
            envio = "JWT "+tokensito
        }
        return envio
    }

    private fun getsocio(u: Usuario?) {
        CoroutineScope(Dispatchers.IO).launch {
            if (u != null && !u.token.isNullOrEmpty()){
                val tokensito:String = u.token
                val socios:Response<List<Socio>> = retrofitService.getsocios(tokensito)
                if (socios.isSuccessful){
                    allsocios = socios.body() ?: emptyList()
                    //showSuccesful()
                    println("funciona")
                }else{
                    //showError()
                    println("ERROR GET SOCIO")
                }
            }
        }
    }

    private suspend fun ComprobarStaff(t: String?):Boolean {
        var a:Boolean = false
        if (t != null && !t.isNullOrEmpty()){
            val tokensito:String = t
            val socios:Response<List<Socio>> = retrofitService.getsocios(tokensito)
            if (socios.isSuccessful){
                a = true
                return a
            }else{
                a = false
                return a
            }
        }

        return a
    }

    private fun checkResult(t: Token?) {
        if (t != null && !t.token.isNullOrEmpty()){
            val tokensito:String = t.token
            runOnUiThread{
                Toast.makeText(this, tokensito,Toast.LENGTH_LONG).show()
            }
        }
    }





    //MOSTRAR TOAST AVISANDO DE FUNCIONAMIENTO CORRECTO
    private fun showSuccesful() {
        runOnUiThread{
            Toast.makeText(this, "Conectando", Toast.LENGTH_SHORT).show()
        }
    }

    //MOSTRAR TOAST AVISANDO DE ERROR
    private fun showError() {
        //Que lo haga en el hilo principal
        runOnUiThread{
            Toast.makeText(this, "LOGIN ERROR", Toast.LENGTH_SHORT).show()
        }
    }

    //INICIALIZAR OBJETOS
    private fun InitView() {
        btnLogin = findViewById(R.id.btnLogin)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etpassword)
        //txtGuia = findViewById(R.id.txtGuia)
    }

}
