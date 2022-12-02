package com.example.lovecornellandroid

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListofSavedLetters(
    var saved : List<Letter>
)
