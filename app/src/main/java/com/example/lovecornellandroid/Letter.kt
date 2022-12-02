package com.example.lovecornellandroid

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Letter(
    var id : String,
    var receiver : String,
    var sender : String,
    val content : String,
    val color: String,
    val timestamp: String
)
