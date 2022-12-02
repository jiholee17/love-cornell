package com.example.lovecornellandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView

class EntryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entry_activity)

        val loginButton: Button = findViewById(R.id.login_button)
        val signupButton: Button = findViewById(R.id.signup_button)

        // Just to test connection to backend
//        getAllLetters {
//            Log.d("results", it.toString())
//        };

//        postLetter("receiver111","sender111","contenttttt","#000000"){
//            Log.d("results",it.toString())
//        }

//        postDraft("1"){
//            Log.d("results",it.toString())
//        }

//        editDraft("receiveredit","senderedit","contentedit","#000022","2"){
//            Log.d("results",it.toString())
//        }

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginAcitivity::class.java)
            startActivity(intent)
        }

        signupButton.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}