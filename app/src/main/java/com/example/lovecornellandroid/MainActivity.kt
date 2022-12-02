package com.example.lovecornellandroid

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.lovecornellandroid.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val token = intent.extras?.getString("token")
        Log.d("main token", token!!)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.forum_item -> {
                    val forumFragment = ForumFragment.newInstance(param1 = token!!)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, forumFragment)
                        .commit()
                }
                R.id.post_item -> {
                    val postFragment = PostFragment.newInstance(param1 = token!!)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, postFragment)
                        .commit()
                }
                R.id.letters_item -> {
                    Log.d("pass token",token.toString())
                    val myLettersFragment = MyLettersFragment.newInstance(param1 = token!!)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, myLettersFragment)
                        .commit()
                }
                else -> {
                    Log.e("Unexpected", "Unexpected" + it.itemId)
                }
            }
            true
        }
    }



}