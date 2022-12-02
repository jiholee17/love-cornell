package com.example.lovecornellandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)

        val emailText : EditText = findViewById(R.id.email_text)
        val passwordText : EditText = findViewById(R.id.password_text)
        val loginAcitivityButton : Button = findViewById(R.id.login_activity_button)
        val switchLoginButton : TextView = findViewById(R.id.switch_login_text)

        var token : String = ""

        loginAcitivityButton.setOnClickListener{
            val email = emailText.text.toString()
            val password = passwordText.text.toString()
            register(email, password){
                // TODO: pass the token to fragments
                token = it.session_token
                Log.d("token",token)


                // Just testing connection to backend
//                createDraft("receiverDraft1","senderDraft1","contentDraft1","#000001",token){
//                    Log.d("draftResult",it.toString())
//                }

//                saveLetter("2",token){
//                    Log.d("savedLetter",it.toString())
//                }

//                deleteDraft(token,"3"){
//                    Log.d("deletedDraft",it.toString())
//                }

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("token",token)
                startActivity(intent)
            }
        }



        val switchLoginText = switchLoginButton.text

        val spannableStringBuilder : SpannableStringBuilder = SpannableStringBuilder(switchLoginText)
        val clickableSpan = object : ClickableSpan(){
            override fun onClick(p0: View) {
                val intent = Intent(p0.context, LoginAcitivity::class.java)
                startActivity(intent)
            }
        }
        spannableStringBuilder.setSpan(clickableSpan,26,32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        switchLoginButton.movementMethod = LinkMovementMethod.getInstance()
        switchLoginButton.text = spannableStringBuilder
    }
}