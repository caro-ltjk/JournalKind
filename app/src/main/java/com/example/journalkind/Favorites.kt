package com.example.journalkind

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button

class Favorites : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        val btnVolver = findViewById<Button>(R.id.btnVolverHome)
        btnVolver.setOnClickListener {
            finish() // Regresa a la pantalla principal
        }
    }
}