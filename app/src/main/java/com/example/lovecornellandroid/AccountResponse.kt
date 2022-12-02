package com.example.lovecornellandroid

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountResponse(
    var session_token : String,
    var session_expiration : String,
    var update_token : String

)
