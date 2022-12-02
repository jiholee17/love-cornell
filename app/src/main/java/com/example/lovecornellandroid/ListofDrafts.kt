package com.example.lovecornellandroid

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListofDrafts(
    var drafts: List<Draft>
)
