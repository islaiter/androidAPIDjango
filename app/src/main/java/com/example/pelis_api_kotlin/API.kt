package com.example.pelis_api_kotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {
    private const val BASE_URL = "http://10.0.2.2:8000/"
    //private const val BASE_URL = "http://192.168.2.56:8000/"
    val retrofitService: ApiService by lazy{
        getRetrofit().create(ApiService::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}