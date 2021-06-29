package com.films
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation


class AppActivity : AppCompatActivity(R.layout.activity_app) {

    lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Films)
        super.onCreate(savedInstanceState)

        navController = Navigation.findNavController(this, R.id.nav_host)

    }
}