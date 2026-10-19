package com.example.journalkind

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val btnVolver = findViewById<Button>(R.id.btnVolverHome)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnAccion = findViewById<Button>(R.id.btnAccionAuth)

        // Volver -> Regresa a la pantalla principal
        btnVolver.setOnClickListener {
            finish()
        }

        // Validación básica de contraseña (mínimo 8 caracteres)
        btnAccion.setOnClickListener {
            val password = etPassword.text.toString()
            if (password.length < 8) {
                Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "¡Acceso exitoso a Journal Kind!", Toast.LENGTH_SHORT).show()
                finish() // Cierra y regresa al home
            }
        }
    }
}