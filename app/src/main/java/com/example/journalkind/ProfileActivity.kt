package com.example.journalkind

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val storage = LocalStorageService(this)

        findViewById<TextView>(R.id.tvPerfilNombre).text = storage.getUserName() ?: ""
        findViewById<TextView>(R.id.tvPerfilCorreo).text = storage.getEmail() ?: ""

        findViewById<Button>(R.id.btnVolverHome).setOnClickListener { finish() }

        findViewById<Button>(R.id.btnCerrarSesion).setOnClickListener {
            AuthService(this).logout()
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()
            finish() // MainActivity restablece el saludo en onResume
        }
    }
}