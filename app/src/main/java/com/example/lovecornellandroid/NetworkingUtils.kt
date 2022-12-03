package com.example.lovecornellandroid

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import java.lang.reflect.Type
import java.net.ProtocolException


fun getAllLetters( callback : (List<Letter>) -> Unit) {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("http://34.162.9.242/letters/")
//        .url("https://api.api-ninjas.com/v1/dictionary?word=" + word.lowercase().replace(" ", ""))
        .get()
//        .addHeader("X-Api-Key", "ccF3u0c3NFZFFPSiusyTYw==xPql8ddx83VBSsgK")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.d("body",e.toString())
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body
                val moshi: Moshi = Moshi.Builder().build()
//
//                val type: Type = Types.newParameterizedType(List::class.java, Letter::class.java)
//                val adapter = moshi.adapter<ListofLetters>(type)
//                val listofLetters : ListofLetters = adapter.fromJson(body!!.source())!!
////                val letters: List<Letter> = adapter.fromJson(body!!.source())!!
//
//                val letters = listofLetters.letters
//                Log.d("body",letters.toString())
//                if (letters != null) {
//                    callback(letters)
//                }

                val jsonAdapter: JsonAdapter<ListofLetters> =
                    moshi.adapter(ListofLetters::class.java)

                try {
                    val listofLetters = jsonAdapter.fromJson(body!!.source())

                    val letters = listofLetters!!.letters
                    Log.d("body",letters.toString())
                    if (letters != null) {
                        callback(letters)
                    }
                }
                catch(e : ProtocolException) {
                    e.printStackTrace()
                    getAllLetters(callback)
                }


            }
        }
    })
}

fun register( email : String, password : String, callback : (AccountResponse) -> Unit) {
    val client = OkHttpClient()
    val jsonObject = JSONObject().apply {
        put("email",email)
        put("password",password)
    }
    val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())

    val request = Request.Builder()
        .url("http://34.162.9.242/register/")
//        .url("https://api.api-ninjas.com/v1/dictionary?word=" + word.lowercase().replace(" ", ""))
        .post(requestBody)
//        .addHeader("X-Api-Key", "ccF3u0c3NFZFFPSiusyTYw==xPql8ddx83VBSsgK")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.d("body",e.toString())
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body
                val moshi: Moshi = Moshi.Builder().build()

                val jsonAdapter: JsonAdapter<AccountResponse> =
                    moshi.adapter(AccountResponse::class.java)

                try {
                    val accountResponse = jsonAdapter.fromJson(body!!.source())

                    Log.d("body",accountResponse.toString())
                    if (accountResponse != null) {
                        callback(accountResponse)
                    }
                }
                catch(e : ProtocolException) {
                    e.printStackTrace()
                    login(email, password, callback)
                }


            }
        }
    })
}

fun postLetter( receiver : String, sender : String, content : String, color : String, callback : (Letter) -> Unit) {
    val client = OkHttpClient()
    val jsonObject = JSONObject().apply {
        put("receiver",receiver)
        put("sender",sender)
        put("content",content)
        put("color",color)

    }
    val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())

    val request = Request.Builder()
        .url("http://34.162.9.242/letters/")
//        .url("https://api.api-ninjas.com/v1/dictionary?word=" + word.lowercase().replace(" ", ""))
        .post(requestBody)
//        .addHeader("X-Api-Key", "ccF3u0c3NFZFFPSiusyTYw==xPql8ddx83VBSsgK")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.d("body",e.toString())
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body
                val moshi: Moshi = Moshi.Builder().build()

                val jsonAdapter: JsonAdapter<Letter> =
                    moshi.adapter(Letter::class.java)

                val letter = jsonAdapter.fromJson(body!!.source())

                Log.d("body",letter.toString())
                if (letter != null) {
                    callback(letter)
                }


            }
        }
    })
}

fun createDraft( receiver : String, sender : String, content : String, color : String, token : String, callback : (Draft) -> Unit) {
    val client = OkHttpClient()
    val jsonObject = JSONObject().apply {
        put("receiver",receiver)
        put("sender",sender)
        put("content",content)
        put("color",color)

    }
    val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())

    val request = Request.Builder()
        .url("http://34.162.9.242/drafts/")
//        .url("https://api.api-ninjas.com/v1/dictionary?word=" + word.lowercase().replace(" ", ""))
        .post(requestBody)
        .addHeader("Authorization", "Bearer $token")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.d("body",e.toString())
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body
                val moshi: Moshi = Moshi.Builder().build()

                val jsonAdapter: JsonAdapter<Draft> =
                    moshi.adapter(Draft::class.java)

                val draft = jsonAdapter.fromJson(body!!.source())

                Log.d("body",draft.toString())
                if (draft != null) {
                    callback(draft)
                }



            }
        }
    })
}

fun postDraft(id : String, callback : (Letter) -> Unit) {
    val client = OkHttpClient()
    val requestBody = "".toRequestBody("application/json".toMediaTypeOrNull())

    val request = Request.Builder()
        .url("http://34.162.9.242/drafts/post/$id")
//        .url("https://api.api-ninjas.com/v1/dictionary?word=" + word.lowercase().replace(" ", ""))
        .post(requestBody)
//        .addHeader("Authorization", "Bearer $token")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.d("body",e.toString())
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body
                val moshi: Moshi = Moshi.Builder().build()

                val jsonAdapter: JsonAdapter<Letter> =
                    moshi.adapter(Letter::class.java)

                val letter = jsonAdapter.fromJson(body!!.source())

                Log.d("body",letter.toString())
                if (letter != null) {
                    callback(letter)
                }


            }
        }
    })
}

fun saveLetter(id : String, token : String, callback : (Letter) -> Unit) {
    val client = OkHttpClient()
    val requestBody = "".toRequestBody("application/json".toMediaTypeOrNull())

    val request = Request.Builder()
        .url("http://34.162.9.242/saved/add/$id")
//        .url("https://api.api-ninjas.com/v1/dictionary?word=" + word.lowercase().replace(" ", ""))
        .post(requestBody)
        .addHeader("Authorization", "Bearer $token")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.d("body",e.toString())
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body
                val moshi: Moshi = Moshi.Builder().build()

                val jsonAdapter: JsonAdapter<Letter> =
                    moshi.adapter(Letter::class.java)

                try {
                    val letter = jsonAdapter.fromJson(body!!.source())

                    Log.d("body",letter.toString())
                    if (letter != null) {
                        callback(letter)
                    }
                }
                catch(e : ProtocolException) {
                    e.printStackTrace()
                    saveLetter(id, token, callback)
                }


            }
        }
    })
}

fun unsaveLetter(id : String, token : String, callback : (Letter) -> Unit) {
    val client = OkHttpClient()
    val requestBody = "".toRequestBody("application/json".toMediaTypeOrNull())

    val request = Request.Builder()
        .url("http://34.162.9.242/saved/remove/$id")
//        .url("https://api.api-ninjas.com/v1/dictionary?word=" + word.lowercase().replace(" ", ""))
        .post(requestBody)
        .addHeader("Authorization", "Bearer $token")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.d("body",e.toString())
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body
                val moshi: Moshi = Moshi.Builder().build()

                val jsonAdapter: JsonAdapter<Letter> =
                    moshi.adapter(Letter::class.java)

                try {
                    val letter = jsonAdapter.fromJson(body!!.source())

                    Log.d("body",letter.toString())
                    if (letter != null) {
                        callback(letter)
                    }
                }
                catch(e : ProtocolException) {
                    e.printStackTrace()
                    unsaveLetter(id, token, callback)
                }


            }
        }
    })
}

fun editDraft(receiver : String, sender : String, content: String, color : String, id : String, callback : (Draft) -> Unit) {
    val client = OkHttpClient()
    val jsonObject = JSONObject().apply {
        put("receiver",receiver)
        put("sender",sender)
        put("content",content)
        put("color",color)

    }
    val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())

    val request = Request.Builder()
        .url("http://34.162.9.242/drafts/edit/$id")
//        .url("https://api.api-ninjas.com/v1/dictionary?word=" + word.lowercase().replace(" ", ""))
        .post(requestBody)
//        .addHeader("Authorization", "Bearer $token")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.d("body",e.toString())
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body
                val moshi: Moshi = Moshi.Builder().build()

                val jsonAdapter: JsonAdapter<Draft> =
                    moshi.adapter(Draft::class.java)

                try {
                    val draft = jsonAdapter.fromJson(body!!.source())

                    Log.d("body",draft.toString())
                    if (draft != null) {
                        callback(draft)
                    }
                }
                catch(e : ProtocolException) {
                    e.printStackTrace()
                    editDraft(receiver, sender, content, color, id, callback)
                }


            }
        }
    })
}

fun deleteDraft(token : String, id : String, callback : (Draft) -> Unit) {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("http://34.162.9.242/drafts/$id")
//        .url("https://api.api-ninjas.com/v1/dictionary?word=" + word.lowercase().replace(" ", ""))
        .delete()
        .addHeader("Authorization", "Bearer $token")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.d("body",e.toString())
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body
                val moshi: Moshi = Moshi.Builder().build()

                val jsonAdapter: JsonAdapter<Draft> =
                    moshi.adapter(Draft::class.java)

                try {
                    val draft = jsonAdapter.fromJson(body!!.source())

                    Log.d("body",draft.toString())
                    if (draft != null) {
                        callback(draft)
                    }
                }
                catch(e : ProtocolException) {
                    e.printStackTrace()
                    deleteDraft(token, id, callback)
                }


            }
        }
    })
}

fun login(email : String, password : String, callback : (AccountResponse) -> Unit) {
    val client = OkHttpClient()
    val jsonObject = JSONObject().apply {
        put("email",email)
        put("password",password)

    }
    val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())

    val request = Request.Builder()
        .url("http://34.162.9.242/login")
//        .url("https://api.api-ninjas.com/v1/dictionary?word=" + word.lowercase().replace(" ", ""))
        .post(requestBody)
//        .addHeader("Authorization", "Bearer $token")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.d("body",e.toString())
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body
                val moshi: Moshi = Moshi.Builder().build()

                val jsonAdapter: JsonAdapter<AccountResponse> =
                    moshi.adapter(AccountResponse::class.java)

                try {
                    val accountResponse = jsonAdapter.fromJson(body!!.source())

                    Log.d("body",accountResponse.toString())
                    if (accountResponse != null) {
                        callback(accountResponse)
                    }
                }
                catch(e : ProtocolException) {
                    e.printStackTrace()
                    login(email, password, callback)
                }
            }
        }
    })
}

fun getDrafts(token : String, callback : (List<Draft>) -> Unit) {
    val client = OkHttpClient.Builder().retryOnConnectionFailure(true).build()

    val request = Request.Builder()
        .url("http://34.162.9.242/drafts")
//        .url("https://api.api-ninjas.com/v1/dictionary?word=" + word.lowercase().replace(" ", ""))
        .get()
        .addHeader("Authorization", "Bearer $token")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.d("body",e.toString())
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body
                val moshi: Moshi = Moshi.Builder().build()

                val jsonAdapter: JsonAdapter<ListofDrafts> =
                    moshi.adapter(ListofDrafts::class.java)

                try {
                    val listofDrafts = jsonAdapter.fromJson(body!!.source())
                    val drafts = listofDrafts!!.drafts
                    Log.d("body",drafts.toString())
                    if (drafts != null) {
                        callback(drafts)
                    }
                }
                catch(e : ProtocolException) {
                    e.printStackTrace()
                    getDrafts(token, callback)
                }

            }
        }
    })
}

fun getSaved(token : String, callback : (List<Letter>) -> Unit) {
    val client = OkHttpClient.Builder().retryOnConnectionFailure(true).build()

    val request = Request.Builder()
        .url("http://34.162.9.242/saved")
//        .url("https://api.api-ninjas.com/v1/dictionary?word=" + word.lowercase().replace(" ", ""))
        .get()
        .addHeader("Authorization", "Bearer $token")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.d("body",e.toString())
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body
                val moshi: Moshi = Moshi.Builder().build()

                val jsonAdapter: JsonAdapter<ListofSavedLetters> =
                    moshi.adapter(ListofSavedLetters::class.java)

                try {
                    val listofSavedLetters = jsonAdapter.fromJson(body!!.source())
                    val letters = listofSavedLetters!!.saved
                    Log.d("body",letters.toString())
                    if (letters != null) {
                        callback(letters)
                    }
                }
                catch(e : ProtocolException) {
                    e.printStackTrace()
                    getSaved(token, callback)
                }

            }
        }
    })
}
