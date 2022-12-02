package com.example.lovecornellandroid

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListofLetters(
    var letters : List<Letter>
)
