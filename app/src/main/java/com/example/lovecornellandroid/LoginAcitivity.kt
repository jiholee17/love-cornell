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
import org.w3c.dom.Text

class LoginAcitivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_acitivity)

        val emailText : EditText = findViewById(R.id.email_text)
        val passwordText : EditText = findViewById(R.id.password_text)
        val loginAcitivityButton : Button = findViewById(R.id.login_activity_button)
        val switchSignupButton : TextView = findViewById(R.id.switch_signup_text)

        var token : String = ""
        loginAcitivityButton.setOnClickListener{
            val email = emailText.text.toString()
            val password = passwordText.text.toString()
            login(email, password){
                // TODO: pass the token to fragments
                token = it.session_token
                Log.d("token",token)

                //Just to test connection to backend
//                createDraft("loginreceiverDraft1","loginsenderDraft1","logincontentDraft1","#000001",token){
//                    Log.d("draftResult",it.toString())
//                }
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("token",token)
                startActivity(intent)

            }



        }

        val switchSignupText = switchSignupButton.text

        val spannableStringBuilder : SpannableStringBuilder = SpannableStringBuilder(switchSignupText)
        val clickableSpan = object : ClickableSpan(){
            override fun onClick(p0: View) {
                val intent = Intent(p0.context, SignupActivity::class.java)
                startActivity(intent)
            }
        }
        spannableStringBuilder.setSpan(clickableSpan,23,30,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        switchSignupButton.movementMethod = LinkMovementMethod.getInstance()
        switchSignupButton.text = spannableStringBuilder
    }
}