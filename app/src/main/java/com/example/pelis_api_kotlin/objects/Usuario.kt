package com.example.pelis_api_kotlin.objects

import java.io.Serializable

data class Usuario(
    val username: String,
    val token: String,
    val isStaff: Boolean,
):Serializable
