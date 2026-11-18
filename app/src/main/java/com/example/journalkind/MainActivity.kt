package com.example.journalkind

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var storage: LocalStorageService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        storage = LocalStorageService(this)

        val btnCrea = findViewById<Button>(R.id.btnIrACuaderno)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        btnCrea.setOnClickListener {
            if (storage.isLoggedIn()) {
                startActivity(Intent(this, JournalActivity::class.java))
            } else {
                Toast.makeText(this, "Inicia sesión para abrir tu diario", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, AuthActivity::class.java))
            }
        }

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_journal -> {
                    val destino = if (storage.isLoggedIn()) Favorites::class.java
                    else AuthActivity::class.java
                    startActivity(Intent(this, destino))
                    false // no deja la pestaña seleccionada
                }
                R.id.nav_profile -> {
                    val destino = if (storage.isLoggedIn()) ProfileActivity::class.java
                    else AuthActivity::class.java
                    startActivity(Intent(this, destino))
                    true
                }
                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        actualizarSaludo()
    }

    private fun actualizarSaludo() {
        val tvBienvenida = findViewById<TextView>(R.id.tvBienvenida)
        val tvFavoritos = findViewById<TextView>(R.id.tvFavoritos)
        val nombre = storage.getUserName()

        if (storage.isLoggedIn() && !nombre.isNullOrBlank()) {
            tvBienvenida.text = "¡Bienvenida, $nombre!"
            tvFavoritos.text = "Favoritos de $nombre"
        } else {
            tvBienvenida.text = getString(R.string.welcome_message)
            tvFavoritos.text = getString(R.string.section_favorites)
        }
    }
}