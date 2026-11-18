package com.example.journalkind

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AuthActivity : AppCompatActivity() {

    private lateinit var authService: AuthService
    private var modoRegistro = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        authService = AuthService(this)

        val btnVolver = findViewById<Button>(R.id.btnVolverHome)
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnAccion = findViewById<Button>(R.id.btnAccionAuth)
        val tvCambiarModo = findViewById<TextView>(R.id.tvCambiarModo)

        fun actualizarModo() {
            etNombre.visibility = if (modoRegistro) View.VISIBLE else View.GONE
            btnAccion.text = if (modoRegistro) "Registrarse" else "Iniciar sesión"
            tvCambiarModo.text = if (modoRegistro) "¿Ya tienes cuenta? Inicia sesión"
            else "¿No tienes cuenta? Regístrate"
        }
        actualizarModo()

        tvCambiarModo.setOnClickListener {
            modoRegistro = !modoRegistro
            actualizarModo()
        }

        btnVolver.setOnClickListener { finish() }

        btnAccion.setOnClickListener {
            val correo = etCorreo.text.toString()
            val password = etPassword.text.toString()

            val result = if (modoRegistro)
                authService.register(etNombre.text.toString(), correo, password)
            else
                authService.login(correo, password)

            when (result) {
                is AuthResult.Success -> {
                    Toast.makeText(this, "¡Bienvenida a Journal Kind!", Toast.LENGTH_SHORT).show()
                    finish() // regresa a MainActivity, que revisa la sesión en onResume
                }
                is AuthResult.Error ->
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}