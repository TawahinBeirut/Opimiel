package com.example.opimiel_frontend.model

import com.example.opimiel_frontend.Subject

data class Response(
    val id: String,
    val value: Boolean,
    val authorId: String,
    val subjectId : String,
    val latitude : Float,
    val longitude: Float
)
