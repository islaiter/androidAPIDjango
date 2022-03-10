package com.example.pelis_api_kotlin.objects

import java.io.Serializable

data class Socio(
    val id: Int,
    val NIF_soc: String,
    val telefono_soc: String,
    val user: String,
): Serializable
