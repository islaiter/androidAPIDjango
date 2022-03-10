package com.example.pelis_api_kotlin.objects

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pelicula(
    @SerializedName("title") val titulo:String,
    @SerializedName("description") val descripcion:String,
    @SerializedName("portada") val portada:String,
    @SerializedName("Trailer") val trailer:String,
):Serializable

