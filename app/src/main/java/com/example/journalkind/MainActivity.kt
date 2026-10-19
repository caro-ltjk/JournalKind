package com.example.journalkind

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCrea = findViewById<Button>(R.id.btnIrACuaderno)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // Boton Crear (JorunalActivity)
        btnCrea.setOnClickListener {
            val intent = Intent(this, JournalActivity::class.java)
            startActivity(intent)
        }

        // Barra de navegación
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true // Actividad actual

                R.id.nav_journal -> { // Actividad Favoritos
                    val intent = Intent(this, Favorites::class.java)
                    startActivity(intent)
                    true
                }

                R.id.nav_profile -> { // Actividad Login / Registro
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}