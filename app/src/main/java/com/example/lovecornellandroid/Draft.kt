package com.example.lovecornellandroid

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Draft(
    var id : String,
    var receiver : String,
    var sender : String,
    val content : String,
    val color: String)
