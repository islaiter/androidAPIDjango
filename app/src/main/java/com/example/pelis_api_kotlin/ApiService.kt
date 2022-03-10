package com.example.pelis_api_kotlin

import com.example.pelis_api_kotlin.objects.Pelicula
import com.example.pelis_api_kotlin.objects.Socio
import com.example.pelis_api_kotlin.objects.Token
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("/api/peliculas/")
    suspend fun getpeliculas(): Response<List<Pelicula>>

    @FormUrlEncoded
    @POST("/loginApi/")
    suspend fun getToken(@Field("username") u: String, @Field("password") p:String): Response<Token>

    @GET("/api/Socio/")
    suspend fun getsocios(@Header("Authorization") authToken: String): Response<List<Socio>>

    @FormUrlEncoded
    @POST("/api/Socio/")
    suspend fun createsocio(@Header("Authorization")authToken: String,
                            @Field("NIF_soc")nif_soc: String,
                            @Field("telefono_soc")telefono_soc : String,
                            @Field("user")user:String): Response<Socio>

    @FormUrlEncoded
    @PUT("/api/Socio/{id}/")
    suspend fun putsocio(@Path("id")id:Int,
                         @Header("Authorization")authToken: String,
                         @Field("NIF_soc")nif_soc: String,
                         @Field("telefono_soc")telefono_soc: String,
                         @Field("user")user:String): Response<Socio>

    @DELETE("/api/Socio/{id}/")
    suspend fun deletesocio(@Header("Authorization")authToken: String,
                            @Path("id")id:Int): Response<Socio>
}

